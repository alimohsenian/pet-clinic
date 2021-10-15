package org.springframework.samples.petclinic.owner;


import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.samples.petclinic.visit.Visit;

import io.cucumber.java.After;
import java.util.List;
import net.bytebuddy.TypeCache.Sort;

import java.util.HashSet;
import java.util.Set;

import java.time.LocalDate;

@RunWith(Theories.class)
public class PetTestTheories {

    private Pet pet;

    @Before
    public void setUp(){
        System.out.println("here");
        pet = new Pet();
    }

    @DataPoints
    public static Set[] firstVector = {
       new HashSet<String>(Arrays.asList("2020-01-08", "2020-02-08")),
       new HashSet<String>(Arrays.asList("2020-01-09", "2019-01-08"))
    };
    
    @DataPoints
    public static String[] secondVector = {"2020-01-07", "2020-03-08"};
    

    @Theory
    public void testGetVisits(Set<String> first, String second){
        ArrayList<String> dates = new ArrayList<>();
        for (String date: first){
            dates.add(date);
        }
        dates.add(second);
        Set<Visit> petVisits = new HashSet<Visit>();
        for (String date: dates){
            Visit visit = new Visit();
            visit.setDate(LocalDate.parse(date));
            petVisits.add(visit);
        }
        Collections.sort(dates,Collections.reverseOrder());
        for (String v: dates){
            System.out.println(v);
        }
        pet.setVisitsInternal(petVisits);
        List<Visit> petGetVisits = pet.getVisits();
        for (int i = 0; i < dates.size(); i++){
            assertEquals(dates.get(i), petGetVisits.get(i).getDate().toString());
        }
    }

    @After
    public void tearDown(){
        pet = null;
    }


}

package org.springframework.samples.petclinic.owner;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
// import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
// import static org.junit.Assert.*;
import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.never;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


public class OwnerTest {

    private Owner owner;
    private Pet cat;
    private Pet dog;
    private Pet duck;
    private Set<Pet> pets;

    

    @BeforeEach
    public void setUp(){
        // System.out.println("before setup");
        owner = new Owner();
        cat = new Pet();
        cat.setName("cat");
        dog = new Pet();
        dog.setName("dog");
        

        pets = new HashSet<Pet>(){{
            add(dog);
            add(cat);
            
        }};
        duck = new Pet();
    }

    @Test 
    public void testGetTelephone(){
        assertEquals(null, owner.getTelephone());
    }

    @Test
    public void testSetTelephone(){
        owner.setTelephone("85");
        assertEquals("85", owner.getTelephone());
    }

    @Test
    public void testGetPetsInternal(){
        assertEquals(0, owner.getPetsInternal().size());
    }

    @Test 
    public void testSetPetsInternal(){
        owner.setPetsInternal(pets);
        assertEquals(2, owner.getPetsInternal().size());
    }

    @Test
    public void testGetPets(){
        owner.setPetsInternal(pets);
        assertEquals(cat, owner.getPets().get(0));
    }

    @Test
    public void testAddPet(){
        owner.setPetsInternal(pets);
        owner.addPet(dog);
        assertEquals(2, owner.getPetsInternal().size());
        owner.addPet(duck);
        assertEquals(3, owner.getPetsInternal().size());
        assertEquals(owner, duck.getOwner());
    }

    @Test
    public void testGetPetNull(){
        owner.setPetsInternal(pets);
        assertEquals(null, owner.getPet("zebra"));
    }

    @Test
    public void testGetPet(){
        owner.setPetsInternal(pets);
        assertEquals(dog, owner.getPet("dOg"));
    }

    @Test
    public void testGetPetNew(){
        owner.setPetsInternal(pets);
        assertEquals(null, owner.getPet("dOg",true));
    }



    @After
    public void tearDown(){
        owner = null;
        cat = null;
        dog = null;
        duck = null;
        pets = null;
    }


}

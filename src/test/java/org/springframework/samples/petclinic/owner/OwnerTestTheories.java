package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Set;
import java.util.HashSet;

@RunWith(Theories.class)
public class OwnerTestTheories{
    
    private Owner owner;
    private Pet cat;
    private Pet dog;
    
    private Set<Pet> pets;

    @Before
    public void setUp(){
        System.out.println("before test");
        owner = new Owner();
         cat = new Pet();
        cat.setName("cat");
         dog = new Pet();
        dog.setName("dog");
         pets = new HashSet<Pet>(){{
            add(dog);
            add(cat);
            
        }};
        owner.setPetsInternal(pets);

    }

    

    @DataPoints
    public static Boolean[] ignoreNew = {true,false};

    @DataPoints
    public static String[] names = {"dog","cat","camel","horse"};

    @Theory
    public void testGetPetTheory(String name, Boolean ignoreNew){
        System.out.println(name);
        System.out.println(ignoreNew);
        Pet pet = owner.getPet(name,ignoreNew);
        assumeNotNull(pet);
        assertTrue(name == pet.getName());
    }

    @After
    public void tearDown(){
        owner = null;
        cat = null;
        dog = null;
        pets = null;
    }
}
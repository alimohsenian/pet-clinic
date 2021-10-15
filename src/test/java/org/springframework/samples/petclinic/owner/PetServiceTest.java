package org.springframework.samples.petclinic.owner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetService;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import static org.junit.jupiter.api.Assertions.*;

// import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

@RunWith(Parameterized.class)
public class PetServiceTest {

	public Integer id;
	public Pet pet;
	public static PetService petService;
	private static PetTimedCache timedCache;

	public PetServiceTest(Integer id, Pet pet) {
		this.id = id;
		this.pet = pet;
		petService = new PetService(timedCache,null,LoggerFactory.getLogger(PetService.class));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> petParams() {
		Pet dog = new Pet();
		dog.setId(1);
		dog.setName("dog");
		Pet cat = new Pet();
		cat.setId(2);
		cat.setName("cat");
		Pet duck = new Pet();
		duck.setId(4);
		duck.setName("duck");
		timedCache = mock(PetTimedCache.class);
		when(timedCache.get(1)).thenReturn(dog);
		when(timedCache.get(2)).thenReturn(cat);
		when(timedCache.get(4)).thenReturn(duck);
		List<Object[]> parameters = new ArrayList<>();
		parameters.add(new Object[] {1,dog});
		parameters.add(new Object[] {2,cat});
		parameters.add(new Object[] {4,duck});
		return parameters;
	}


	@Test
	public void testFindPet() {
		System.out.println("id: "+id);
		System.out.println("pet: "+pet.getName());
		System.out.println("output: "+petService.findPet(id).getName());

		assertEquals(pet.getName(), petService.findPet(id).getName());


	}
}




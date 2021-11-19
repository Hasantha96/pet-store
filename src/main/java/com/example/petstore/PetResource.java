package com.example.petstore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.dto.AddPetRequest;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

//view all pets
@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = getPetList();
		return Response.ok(pets).build();
	}

	private List<Pet> getPetList() {
		List<Pet> pets = new ArrayList<Pet>();
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		Pet pet4 = new Pet();
		pet4.setPetId(4);
		pet4.setPetAge(6);
		pet4.setPetName("Boola");
		pet4.setPetType("Dog");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);
		return pets;
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })

	//get pet by pet Id
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		List<Pet> pets=getPetList().stream()
				.filter(pet -> pet.getPetId().equals(petId))
				.distinct()
				.collect(Collectors.toList());

		if (pets.isEmpty()){
			return Response.status(Status.BAD_REQUEST).build();
		}else
		return Response.ok(pets).build();
		
	}

	//get pets by name
	@GET
	@Path("/name/{petName}")
	public Response getPetName(@PathParam("petName") String petName) {

		List<Pet> pets=getPetList().stream()
				.filter(pet -> pet.getPetName().equals(petName))
				.collect(Collectors.toList());

		if (pets.isEmpty()){
			return Response.status(Status.BAD_REQUEST).build();
		}else
			return Response.ok(pets).build();

	}
	//get pets by age
	@GET
	@Path("/age/{petAge}")
	public Response getPetAge(@PathParam("petAge") Integer petAge) {
		List<Pet> pets=getPetList().stream()
				.filter(pet -> pet.getPetAge().equals(petAge))
				.collect(Collectors.toList());

		if (pets.isEmpty()){
			return Response.status(Status.BAD_REQUEST).build();
		}else
			return Response.ok(pets).build();

	}
	//add new pet
	@POST
	@Path("/add pet")
	public Response addPet(@RequestBody AddPetRequest addPetRequest) {
		if (addPetRequest == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<Pet> pets=getPetList();
		int petId=pets.size();
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(addPetRequest.getPetAge());
		pet.setPetName(addPetRequest.getPetName());
		pet.setPetType(addPetRequest.getPetType());
		pets.add(pet);

		return Response.ok(pets).build();

	}
	//delete pet
	@GET
	@Path("/delete pet/{petId}")
	public Response deletePet(@PathParam("petId") int petId) {
		if (petId <0 ) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<Pet> pets=getPetList().stream()
				.filter(pet -> !(pet.getPetId().equals(petId)))
				.distinct()
				.collect(Collectors.toList());

		return Response.ok(pets).build();

	}

}

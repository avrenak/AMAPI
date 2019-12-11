package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.place.PlaceFull;
import com.formation.dto.place.PlaceLight;
import com.formation.persistence.entities.Place;
import com.formation.services.IPlaceService;

@RestController
@RequestMapping(path = "/api/private/places")
public class PlaceController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	IPlaceService service;

	@GetMapping 
	public List<PlaceLight> getAll(){

		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, PlaceLight.class))
				.collect(Collectors.toList()) ; 
			}
	
	@RequestMapping(path="/{identifiant}", method = RequestMethod.GET )
	public PlaceFull findOne(@PathVariable(name = "identifiant") Long id) {
           return  mapper.map(service.findOne(id), PlaceFull.class);
	}
	
	@DeleteMapping (path = "/{identifiant}")
	public PlaceFull deleteById(@PathVariable(name = "identifiant") Long id) {
        return  mapper.map(service.deleteById(id), PlaceFull.class);
   }
	
	@PostMapping
	public PlaceFull save(@RequestBody PlaceFull placeF) {
		Place place = mapper.map(placeF, Place.class);
		place = service.save(place);
		
		PlaceFull placeFSauve = mapper.map(place, PlaceFull.class);
		return placeFSauve;
	}
	
		
	
}
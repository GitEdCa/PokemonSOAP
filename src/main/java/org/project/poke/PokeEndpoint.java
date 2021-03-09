package org.project.poke;

import java.util.Arrays;

import org.project.poke.service.BaseExperienceRequest;
import org.project.poke.service.BaseExperienceResponse;
import org.project.poke.service.GetAbilitiesRequest;
import org.project.poke.service.GetAbilitiesResponse;
import org.project.poke.service.HeldItemsRequest;
import org.project.poke.service.HeldItemsResponse;
import org.project.poke.service.IdRequest;
import org.project.poke.service.IdResponse;
import org.project.poke.service.LocationAreaEncounters;
import org.project.poke.service.LocationAreaEncountersRequest;
import org.project.poke.service.LocationAreaEncountersResponse;
import org.project.poke.service.NameRequest;
import org.project.poke.service.NameResponse;
import org.project.poke.service.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokeEndpoint {

	private static final String NAMESPACE_URI = "http://project.org/Poke/service";

	private static final Logger logger = LoggerFactory.getLogger(PokeEndpoint.class);

	@Value("${pokemon.api.url}")
	private static String POKEMON_API = "https://pokeapi.co/api/v2/pokemon/";

	@Autowired
	private RestTemplate restTemplate;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse abilities(@RequestPayload GetAbilitiesRequest request) {
		logger.info("Called abilities foir {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		response.getAbilities().addAll(p.getAbilities());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "base_experienceRequest")
	@ResponsePayload
	public BaseExperienceResponse base_experience(@RequestPayload BaseExperienceRequest request) {
		logger.info("Called abilities foir {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		BaseExperienceResponse response = new BaseExperienceResponse();
		response.setBaseExperience(p.getBaseExperience());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "held_itemsRequest")
	@ResponsePayload
	public HeldItemsResponse held_items(@RequestPayload HeldItemsRequest request) {
		logger.info("Called held_items foir {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		HeldItemsResponse response = new HeldItemsResponse();
		response.getHeldItems().addAll(p.getHeldItems());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "idRequest")
	@ResponsePayload
	public IdResponse id(@RequestPayload IdRequest request) {
		logger.info("Called id foir {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		IdResponse response = new IdResponse();
		response.setId(p.getId());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "nameRequest")
	@ResponsePayload
	public NameResponse name(@RequestPayload NameRequest request) {
		logger.info("Called abilities foir {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		NameResponse response = new NameResponse();
		response.setName(p.getName());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "location_area_encountersRequest")
	@ResponsePayload
	public LocationAreaEncountersResponse location_area_encounters(@RequestPayload LocationAreaEncountersRequest request) {
		logger.info("Called location_area_encounters {} {}", request.getPokemon(), POKEMON_API);
		String pokemon = request.getPokemon();
		Pokemon p = callPokemonApi(POKEMON_API + pokemon);
		LocationAreaEncounters[] lae = restTemplate.getForObject(p.getLocationAreaEncounters(), LocationAreaEncounters[].class);
		LocationAreaEncountersResponse response = new LocationAreaEncountersResponse();
		response.getLocationAreaEncounters().addAll(Arrays.asList(lae));
		return response;
	}
	
	private Pokemon callPokemonApi(String url) {
		return restTemplate.getForObject(url, Pokemon.class);
	}
	
	
	
}

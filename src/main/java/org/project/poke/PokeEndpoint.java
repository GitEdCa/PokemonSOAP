package org.project.poke;

import org.project.poke.service.Abilities;
import org.project.poke.service.Ability;
import org.project.poke.service.GetAbilitiesRequest;
import org.project.poke.service.GetAbilitiesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokeEndpoint {

	private static final String NAMESPACE_URI = 
			"http://project.org/Poke/service";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse abilities(
			@RequestPayload GetAbilitiesRequest request) {
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		Abilities abs = new Abilities();		
		abs.setIsHidden(false);
		abs.setSlot(1);
		Ability ab = new Ability();
		ab.setName("Ab1");
		ab.setUrl("URL");
		abs.setAbility(ab);
		response.getAbilities().add(abs);
		return response;
	}
}

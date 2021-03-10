# PokemonSOAP
SOAP Web Service made in Spring Boot to fetch pokemon data in real time
from [PokeApi](https://pokeapi.co/). Currenlty in only has 5 endpoints:
* abilities
* base_experience
* held_items
* id
* name
* location_area_encounters

Each endpoint accepts only one string(pokemon) as request.
Every request(ip & method) is being logged into a H2 database.

## Testing locally
Check request.xml file as example for a request

In linux we can do the following command to test locally:
```
   curl --header "Content-Type: text/xml;charset=UTF-8" --data
   @request.xml http://localhost:8080/pokemon/
```

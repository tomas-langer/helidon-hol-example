
package io.helidon.examples.quickstart.mp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A JAX-RS controller handling requests for vaccinated pets.
 */
@Path("/vaccinated")
@RequestScoped
public class VaccinatedResource {
    private final VaccinatedPets vaccinatedPets;

    /**
     * Using constructor injection to get a configuration property.
     * By default this gets the value from META-INF/microprofile-config
     *
     * @param greetingConfig the configured greeting message
     */
    @Inject
    public VaccinatedResource(VaccinatedPets greetingConfig) {
        this.vaccinatedPets = greetingConfig;
    }

    /**
     * Return true if pet is vaccinated.
     *
     * @param name name of pet
     * @return {@code true} if vaccinated
     */
    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonValue isVaccinated(@PathParam("name") String name) {
        if (vaccinatedPets.isVaccinated(name)) {
            return JsonValue.TRUE;
        }
        return JsonValue.FALSE;
    }
}

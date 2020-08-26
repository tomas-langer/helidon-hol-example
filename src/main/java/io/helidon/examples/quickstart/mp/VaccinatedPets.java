
package io.helidon.examples.quickstart.mp;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Bean that checks vaccination by name.
 */
@ApplicationScoped
public class VaccinatedPets {
    private final Set<String> vaccinated = new HashSet<>();

    /**
     * Create new vaccinated pets bean.
     *
     * @param vaccinated list of vaccinated pets from configuration
     */
    @Inject
    public VaccinatedPets(@ConfigProperty(name = "pets.vaccinated") Set<String> vaccinated) {
        this.vaccinated.addAll(vaccinated);
    }

    boolean isVaccinated(String name) {
        return vaccinated.contains(name);
    }
}

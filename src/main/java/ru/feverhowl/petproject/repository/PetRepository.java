package ru.feverhowl.petproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feverhowl.petproject.entity.Pet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Dmitrii Zolotarev
 */
public interface PetRepository extends JpaRepository<Pet, String> {

    List<Pet> findAllBySpeciesEquals(String species);

    List<Pet> findAllBySpeciesEqualsAndBirthDayGreaterThanAndBirthDayLessThan(String species, LocalDate bd_from, LocalDate bd_to);

    List<Pet> findAllByNameEquals(String name);

    Optional<Pet> findById(String id);

}

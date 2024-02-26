package ru.feverhowl.petproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feverhowl.petproject.Exception.BirthDayTooEarlyException;
import ru.feverhowl.petproject.Exception.PetNotFoundException;
import ru.feverhowl.petproject.dto.PetDto;
import ru.feverhowl.petproject.entity.Pet;
import ru.feverhowl.petproject.repository.PetRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dmitrii Zolotarev
 */
@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Pet save(PetDto petDto) throws BirthDayTooEarlyException {
        if (petDto.getBirthDay().getYear() <= 2000) throw new BirthDayTooEarlyException("Pet's birth bay is too early.");
        Pet pet = new Pet()
                .setId(UUID.randomUUID().toString())
                .setSpecies(petDto.getSpecies())
                .setName(petDto.getName())
                .setBirthDay(petDto.getBirthDay())
                .setCreatedAt(LocalDateTime.now());
        return petRepository.save(pet);
    }

    public Iterable<Pet> getAll() {
        return petRepository.findAll();
    }

    public Iterable<Pet> getAllCats() {
        return petRepository.findAllBySpeciesEquals("cat");
    }

    public Iterable<Pet> getAllDogs() {
        return petRepository.findAllBySpeciesEquals("dog");
    }

    public Iterable<Pet> getAllSnakes() {
        return petRepository.findAllBySpeciesEquals("snake");
    }

    public Iterable<Pet> getPets(String species, String bd_from, String bd_to) {
        return petRepository.findAllBySpeciesEqualsAndBirthDayGreaterThanAndBirthDayLessThan(species, LocalDate.parse(bd_from), LocalDate.parse(bd_to));
    }

    public Iterable<Pet> getPetsByName(String name) {
        return petRepository.findAllByNameEquals(name);
    }

    public Optional<Pet> getOne(String id) {
        return petRepository.findById(id);
    }

    public void delete(String id) throws PetNotFoundException {
        if (petRepository.findById(id).isEmpty()) throw new PetNotFoundException("Pet with id=" + id + " not found.");
        petRepository.deleteById(id);
    }

    public Pet updateOne(Pet pet) throws PetNotFoundException {
        if (petRepository.findById(pet.getId()).isEmpty()) throw new PetNotFoundException("Pet with id=" + pet.getId() + " not found.");
        Pet result = new Pet()
                .setId(pet.getId())
                .setSpecies(pet.getSpecies())
                .setName(pet.getName())
                .setBirthDay(pet.getBirthDay())
                .setCreatedAt(pet.getCreatedAt());
        petRepository.deleteById(pet.getId());
        return petRepository.save(result);
    }
}

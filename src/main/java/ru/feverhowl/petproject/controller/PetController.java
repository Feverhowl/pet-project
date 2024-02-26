package ru.feverhowl.petproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.feverhowl.petproject.Exception.BirthDayTooEarlyException;
import ru.feverhowl.petproject.Exception.PetNotFoundException;
import ru.feverhowl.petproject.dto.PetDto;
import ru.feverhowl.petproject.entity.Pet;
import ru.feverhowl.petproject.service.PetService;

import java.util.Optional;

/**
 * @author Dmitrii Zolotarev
 */
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService service;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PetDto pet) {
        try {
            return ResponseEntity.ok(service.save(pet));
        } catch (BirthDayTooEarlyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while saving a pet.");
        }
    }

    @GetMapping
    public Iterable<Pet> getAll() {
        return service.getAll();
    }

    @GetMapping("/allCats")
    public Iterable<Pet> getAllCats() {
        return service.getAllCats();
    }

    @GetMapping("/allDogs")
    public Iterable<Pet> getAllDogs() {
        return service.getAllDogs();
    }

    @GetMapping("/allSnakes")
    public Iterable<Pet> getAllSnakes() {
        return service.getAllSnakes();
    }

    @GetMapping("/getPets")
    public Iterable<Pet> getPets(@RequestParam String species, String bd_from, String bd_to) {
        return service.getPets(species, bd_from, bd_to);
    }

    @GetMapping("/getPetsByName")
    public Iterable<Pet> getPetsByName(@RequestParam String name) {
        return service.getPetsByName(name);
    }

    @GetMapping("/getOne")
    public Optional<Pet> getOne(@RequestParam String id) {
        return service.getOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Pet with id=" + id + " was deleted successfully.");
        } catch (PetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while deleting a pet.");
        }
    }

    @PostMapping("/updateOne")
    public ResponseEntity<Object> updateOne(@RequestBody Pet pet) {
        try {
            return ResponseEntity.ok(service.updateOne(pet));
        } catch (PetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while updating a pet.");
        }
    }
}

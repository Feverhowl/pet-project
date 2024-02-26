package ru.feverhowl.petproject.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Dmitrii Zolotarev
 */
@Data
public class PetDto {
    private String species;
    private String name;
    private LocalDate birthDay;
}

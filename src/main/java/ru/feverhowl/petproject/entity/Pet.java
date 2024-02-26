package ru.feverhowl.petproject.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Dmitrii Zolotarev
 */
@Entity
@Data
@Accessors(chain = true)
public class Pet {

    @Id
    private String id;
    private String species;
    private String name;
    private LocalDate birthDay;
    private LocalDateTime createdAt;
}

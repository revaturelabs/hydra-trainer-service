package com.revature.hydratrainerservice.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerId;

    private String firstName;
    private String lastName;
    private String title;
    private String email;

    @ColumnDefault("true")
    private boolean active;

    // TODO: Certifications

    // TODO: Resume's

    @Enumerated(EnumType.STRING)
    private Tier tier;

}

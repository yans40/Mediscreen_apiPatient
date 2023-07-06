package com.openclassrooms.mediscreen.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.mediscreen.constant.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotBlank(message = "Le prénom est requis")
    @Column(name = "prenom")
    private String prenom;

    @NotBlank(message = "Le nom est requis")
    @Column(name = "nom")
    private String nom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Column(name = "date_de_naissance")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeNaissance;

    @NotNull(message = "Le genre est requis")
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "adresse_postale")
    private String adressePostale;

    @Column(name = "numero_de_telephone")
    private String numeroDeTelephone;

}

package com.openclassrooms.mediscreen.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.mediscreen.constant.Genre;
import jakarta.persistence.*;
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
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "nom")
    private String nom;
    @Column(name = "date_de_naissance")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeNaissance;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "adresse_postale")
    private String adressePostale;
    @Column(name = "numero_de_telephone")
    private String numeroDeTelephone;

}

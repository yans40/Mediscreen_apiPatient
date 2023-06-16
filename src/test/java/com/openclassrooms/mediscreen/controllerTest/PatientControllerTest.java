package com.openclassrooms.mediscreen.controllerTest;

import com.openclassrooms.mediscreen.constant.Genre;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.services.PatientService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Jackson2ObjectMapperBuilder objectMapper;
    @Autowired
    PatientService patientService;


    @Test
    public void testCreatePatient() throws Exception {
        // Créez un objet Patient à utiliser pour le test
        Patient patient = new Patient();
        patient.setPrenom("Jean");
        patient.setNom("Dupont");
        patient.setDateDeNaissance(LocalDate.of(2000, 12, 12));
        patient.setGenre(Genre.F);
        patient.setAdressePostale("10 rue du test");
        patient.setNumeroDeTelephone("123-456-789");

        // Effectuez la requête POST
        this.mockMvc.perform(post("/apipatient/patients")
                        .content(objectMapper.build().writeValueAsString(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPatientById() throws Exception {
        Patient patient = new Patient();
        patient.setPrenom("Jean");
        patient.setNom("Dupont");
        patient.setDateDeNaissance(LocalDate.of(2000, 12, 12));
        patient.setGenre(Genre.F);
        patient.setAdressePostale("10 rue du test");
        patient.setNumeroDeTelephone("123-456-789");
        Patient createdPatient = patientService.savePatient(patient);

        // Effectuez la requête GET avec l'ID du patient
        mockMvc.perform(get("/apipatient/patients/{id}", createdPatient.getId()))
                .andExpect(status().isOk());
    }



    @Test
    public void testGetAllPatients() throws Exception {
        // Effectuer la requête GET sur l'endpoint /patients
        mockMvc.perform(get("/apipatient/patients"))
                .andExpect(status().isOk()) // Vérifier le statut de la réponse HTTP (200 OK)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Vérifier le type de contenu de la réponse
                .andExpect(jsonPath("$").isArray()); // Vérifier que la réponse est un tableau JSON
    }




    @Test
    public void testUpdatePatient() throws Exception {
        // Créer un patient existant avec les attributs à mettre à jour
        Patient existingPatient = new Patient();
        existingPatient.setId(2L);
        existingPatient.setPrenom("John");
        existingPatient.setNom("Doe");
        existingPatient.setDateDeNaissance(LocalDate.of(1990, 1, 1));
        existingPatient.setAdressePostale("123 rue de test");
        existingPatient.setNumeroDeTelephone("987-654-321");
        existingPatient.setGenre(Genre.M);

        // Enregistrer le patient existant dans la base de données (par exemple, via le repository)
        Patient savedPatient = patientService.savePatient(existingPatient);

        // Effectuer la requête PUT sur l'endpoint /patients/{id} avec les données mises à jour
        mockMvc.perform(put("/apipatient/patients/{id}", savedPatient.getId())
                        .content(objectMapper.build().writeValueAsString(savedPatient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.prenom").value(existingPatient.getPrenom()))
                .andExpect(jsonPath("$.nom").value(existingPatient.getNom()))
                .andExpect(jsonPath("$.dateDeNaissance").value("01/01/1990"))
                .andExpect(jsonPath("$.adressePostale").value(existingPatient.getAdressePostale()))
                .andExpect(jsonPath("$.numeroDeTelephone").value(existingPatient.getNumeroDeTelephone()))
                .andExpect(jsonPath("$.genre").value(existingPatient.getGenre().toString()));
    }

    @Test
    public void testDeletePatient() throws Exception {
        // Recherchez un patient existant dans la base de données
        Long patientId = patientService.getAllPatients().get(0).getId();

        // Effectuez la requête DELETE
        this.mockMvc.perform(delete("/apipatient/patients/{id}", patientId))
                .andExpect(status().isOk());

        // Vérifiez que le patient a été supprimé en vérifiant qu'il n'existe plus dans la base de données
        assertFalse(patientService.getPatientById(patientId).isPresent());
    }




}

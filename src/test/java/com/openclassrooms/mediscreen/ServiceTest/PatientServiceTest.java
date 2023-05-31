package com.openclassrooms.mediscreen.ServiceTest;

import com.openclassrooms.mediscreen.Entities.Patient;
import com.openclassrooms.mediscreen.Repositories.PatientRepository;
import com.openclassrooms.mediscreen.Services.PatientService;
import com.openclassrooms.mediscreen.constant.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void savePatient_shouldSavePatient() {
        // Créer un patient fictif pour le test
        Patient patient = new Patient();
        patient.setPrenom("John");
        patient.setNom("Doe");
        patient.setDateDeNaissance(LocalDate.of(1990, 1, 1));
        patient.setGenre(Genre.MASCULIN);
        patient.setAdressePostale("123 rue Test");
        patient.setNumeroDeTelephone("1234567890");

        // Définir le comportement du repository mock lors de l'appel à save()
        when(patientRepository.save(patient)).thenReturn(patient);

        // Appeler la méthode savePatient() du service
        Patient savedPatient = patientService.savePatient(patient);

        // Vérifier si le patient a été sauvegardé avec succès
        assertNotNull(savedPatient);
        assertEquals("John", savedPatient.getPrenom());
        assertEquals("Doe", savedPatient.getNom());
        assertEquals("123 rue Test", savedPatient.getAdressePostale());
        // Vérifier les autres attributs du patient

        // Vérifier si la méthode save() du repository a été appelée une fois
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void getPatient_shouldGetPatientById(){
        Patient patient = new Patient();
        patient.setId(1l);
        patient.setPrenom("Jane");
        patient.setNom("Gi");
        patient.setDateDeNaissance(LocalDate.of(1991, 1, 1));
        patient.setGenre(Genre.FEMININ);
        patient.setAdressePostale("02 rue Test");
        patient.setNumeroDeTelephone("1234567890");

        when(patientRepository.findById(1l)).thenReturn(Optional.of(patient));

        Optional<Patient> patientTest = patientService.getPatientById(1l);

        assertEquals("Jane", patientTest.get().getPrenom());
        assertEquals("Gi", patientTest.get().getNom());

    }
    @Test
    public void getAllPatients_shouldReturnAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 1, 1), Genre.FEMININ, "Adresse 1", "1234567890"));
        patients.add(new Patient(2L, "John", "Smith", LocalDate.of(1985, 5, 10), Genre.MASCULIN, "Adresse 2", "9876543210"));

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        assertEquals("Jane", result.get(0).getPrenom());
        assertEquals("Doe", result.get(0).getNom());
        assertEquals("John", result.get(1).getPrenom());
        assertEquals("Smith", result.get(1).getNom());

        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void deletePatient_shouldDeletePatient() {
        Long patientId = 1L;

        patientService.deletePatient(patientId);

        verify(patientRepository, times(1)).deleteById(patientId);
    }
}


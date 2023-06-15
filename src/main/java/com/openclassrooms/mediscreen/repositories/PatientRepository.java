package com.openclassrooms.mediscreen.repositories;

import com.openclassrooms.mediscreen.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long > {

}

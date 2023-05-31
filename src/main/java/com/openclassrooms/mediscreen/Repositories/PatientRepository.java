package com.openclassrooms.mediscreen.Repositories;

import com.openclassrooms.mediscreen.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long > {

}

package com.medilabo.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.medilabo.domain.Patient;
import com.medilabo.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}

	public Patient findById(Integer id) throws NotFoundException {
		Optional<Patient> patientOpt = patientRepository.findById(id);
		if (patientOpt.isPresent()) {
			return patientOpt.get();
		} else {
			throw new NotFoundException();
		}
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient findByNom(String nom) throws NotFoundException {
		Optional<Patient> patientOpt = patientRepository.findByNom(nom);
		if (patientOpt.isPresent()) {
			return patientOpt.get();
		} else {
			throw new NotFoundException();
		}
	}

	public int getAge(String nom) throws NotFoundException {
		Patient patient = findByNom(nom);
		Date dateBirth = patient.getDate_de_naissance();
		LocalDate dateOfBirth = LocalDate.parse(dateBirth.toString());
		int age = dateOfBirth.until(LocalDate.now()).getYears();
		return age;
	}
}

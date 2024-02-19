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

	public Optional<Patient> findById(Integer id) throws NotFoundException {
		Optional<Patient> patientOpt = patientRepository.findById(id);
		if (patientOpt.isPresent()) {
			return patientOpt;
		} else {
			throw new NotFoundException();
		}
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Optional<Patient> findByNom(String nom) {
		return patientRepository.findByNom(nom);
	}

	public int getAge(String nom) {
		Optional<Patient> dateBirthOpt = findByNom(nom);
		Date dateBirth = dateBirthOpt.get().getDate_de_naissance();
		LocalDate dateOfBirth = LocalDate.parse(dateBirth.toString());
		LocalDate now = LocalDate.now();
		int age = dateOfBirth.until(now).getYears();
		return age;
	}
}

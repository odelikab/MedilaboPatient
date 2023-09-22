package com.medilabo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.domain.Patient;
import com.medilabo.repositories.PatientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientRepository patientRepository;

	@GetMapping("/list")
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Patient> getPatientInfo(@PathVariable("id") Integer id) {

		return patientRepository.findById(id);
	}

	@PutMapping("/patient/{id}")
	public Patient updatePatient(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result) {
		Optional<Patient> patientFound = patientRepository.findById(id);

		if (patientFound.isPresent()) {
			patientFound.get().setAdresse_postale(patient.getAdresse_postale());
			patientFound.get().setGenre(patient.getGenre());
			patientFound.get().setNumero_telephone(patient.getNumero_telephone());
		}
		return patientRepository.save(patientFound.get());

	}

	@PostMapping("/patient")
	public Patient addPatient(@Valid @RequestBody Patient patient) {
		return patientRepository.save(patient);
	}
}

package com.medilabo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.domain.Patient;
import com.medilabo.services.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@GetMapping("/list")
	public List<Patient> getPatients() {
		return patientService.findAllPatients();
	}

	@GetMapping("/{id}")
	public Optional<Patient> getPatientInfo(@PathVariable("id") Integer id) throws NotFoundException {
		return patientService.findById(id);
	}

	@GetMapping("/nom/{nom}")
	public Optional<Patient> getPatientbyName(@PathVariable("nom") String nom) {
		return patientService.findByNom(nom);
	}

	@GetMapping("/age/{nom}")
	public int getAge(@PathVariable("nom") String nom) {
		return patientService.getAge(nom);
	}

	@PutMapping("/patient/{id}")
	public Patient updatePatient(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result)
			throws Exception {
		Optional<Patient> patientFound = patientService.findById(id);

		if (patientFound.isPresent()) {
			patientFound.get().setAdresse_postale(patient.getAdresse_postale());
			patientFound.get().setGenre(patient.getGenre());
			patientFound.get().setNumero_telephone(patient.getNumero_telephone());
			return patientService.savePatient(patientFound.get());
		} else
			throw new Exception("patient not found");

	}

	@PostMapping("/patient")
	public Patient addPatient(@Valid @RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}
}

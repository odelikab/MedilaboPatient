package com.medilabo.controller;

import java.util.List;

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
	public Patient getPatientInfo(@PathVariable("id") Integer id) throws NotFoundException {
		return patientService.findById(id);
	}

	@GetMapping("/nom/{nom}")
	public Patient getPatientbyName(@PathVariable("nom") String nom) throws NotFoundException {
		return patientService.findByNom(nom);
	}

	@GetMapping("/age/{nom}")
	public int getAge(@PathVariable("nom") String nom) throws NotFoundException {
		return patientService.getAge(nom);
	}

	@PutMapping("/patient/{id}")
	public Patient updatePatient(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result)
			throws Exception {
		Patient patientFound = patientService.findById(id);

		patientFound.setAdresse_postale(patient.getAdresse_postale());
		patientFound.setGenre(patient.getGenre());
		patientFound.setNumero_telephone(patient.getNumero_telephone());
		return patientService.savePatient(patientFound);
	}

	@PostMapping("/patient")
	public Patient addPatient(@Valid @RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}
}

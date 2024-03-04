package com.medilabo.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "prenom is mandatory")
	private String prenom;
	@NotBlank(message = "nom is mandatory")
	private String nom;
	// @NotBlank(message = "date de naissance is mandatory")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_de_naissance;
	@NotBlank(message = "gender is mandatory")
	@Enumerated(EnumType.STRING)
	private PatientGender genre;
	private String adresse_postale;
	private String numero_telephone;

}

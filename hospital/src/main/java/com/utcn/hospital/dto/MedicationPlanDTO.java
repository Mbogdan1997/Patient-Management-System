package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.Date;

import com.utcn.hospital.entity.MedicationPlan;

public class MedicationPlanDTO extends IdDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8849756838894853812L;
	private Date startDate;
	private Date finishDate;
	private DoctorDTO doctorDTO;
	private PatientDTO patientDTO;
	
	
	
	public MedicationPlanDTO() {
		super();
	}

	public MedicationPlanDTO(int id) {
		super(id);
	}

	public MedicationPlanDTO(MedicationPlan medicationPlan) {
		super(medicationPlan.getId());
		this.startDate=medicationPlan.getStartDate();
		this.finishDate=medicationPlan.getFinishDate();
		this.doctorDTO=new DoctorDTO(medicationPlan.getDoctor());
		this.patientDTO=new PatientDTO(medicationPlan.getPatient());
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}
	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}
	public PatientDTO getPatientDTO() {
		return patientDTO;
	}
	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}
	
	

}

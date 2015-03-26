package com.example.sarpkaya.annoyer;

import ca.uhn.fhir.model.primitive.DateTimeDt;


public class MedicationModel {
    private String name;
    private Long sctid;
    private DateTimeDt medicationTimeToBeTaken;
    private DateTimeDt medicationTakenTime;
    private Boolean medicationTaken;

    public MedicationModel() {
        medicationTaken = false;
    }

    public Boolean isMedicationTaken() {
        return medicationTaken;
    }

    public void setMedicationTaken(Boolean medicationTaken) {
        this.medicationTaken = medicationTaken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSctid() {
        return sctid;
    }

    public void setSctid(Long sctid) {
        this.sctid = sctid;
    }

    public DateTimeDt getMedicationTimeToBeTaken() {
        return medicationTimeToBeTaken;
    }

    public void setMedicationTimeToBeTaken(DateTimeDt medicationTimeToBeTaken) {
        this.medicationTimeToBeTaken = medicationTimeToBeTaken;
    }

    public DateTimeDt getMedicationTakenTime() {
        return medicationTakenTime;
    }

    public void setMedicationTakenTime(DateTimeDt medicationTakenTime) {
        this.medicationTakenTime = medicationTakenTime;
    }

    public void takeMedication() {
        this.setMedicationTakenTime(DateTimeDt.withCurrentTime());
    }
}

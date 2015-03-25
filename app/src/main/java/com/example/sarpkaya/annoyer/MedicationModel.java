package com.example.sarpkaya.annoyer;

import ca.uhn.fhir.model.primitive.DateTimeDt;


public class MedicationModel {
    private String name;
    private Long sctid;
    private DateTimeDt medicationTimeToBeTaken;
    private DateTimeDt medicationTakenTime;

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

    public boolean isMedicationTaken() {
        return getMedicationTakenTime() != null;
    }

    public DateTimeDt getMedicationTakenTime() {
        return medicationTakenTime;
    }

    public void setMedicationTakenTime(DateTimeDt medicationTakenTime) {
        this.medicationTakenTime = medicationTakenTime;
    }

    public void takeMedication() {
        FHIRInterface.getInstance().tookMedication(sctid);
        this.setMedicationTakenTime(DateTimeDt.withCurrentTime());
    }
}

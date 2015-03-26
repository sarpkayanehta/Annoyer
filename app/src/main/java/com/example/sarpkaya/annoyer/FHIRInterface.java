package com.example.sarpkaya.annoyer;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationAdministration;
import ca.uhn.fhir.model.dstu2.valueset.MedicationAdministrationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.rest.client.IGenericClient;


public class FHIRInterface {
    private static FHIRInterface ourInstance = new FHIRInterface();
    private final FhirContext ctx;
    private final String serverBase = "http://fhir-dev.healthintersections.com.au/open";
    private final IGenericClient client;

    public static FHIRInterface getInstance() {
        return ourInstance;
    }

    private FHIRInterface() {
        ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverBase);
    }

    private Medication createMedication(long sctid, String name) {
        Medication medication = new Medication();
        medication.setName(name);
        List<CodingDt> codedMedicine = new ArrayList<>();
        // setSystem is wrong for v3, need to find the right URI
        codedMedicine.add(new CodingDt().setDisplay(name).setCode(String.valueOf(sctid)).setSystem("http://nehta.gov.au/amt/v3"));
        medication.setCode(new CodeableConceptDt().setCoding(codedMedicine));

        return medication;
    }

    private MedicationAdministration createMedicationAdministration() {

        MedicationAdministration medicationAdministration = new MedicationAdministration();
        medicationAdministration.setEffectiveTime(DateTimeDt.withCurrentTime()).setStatus(MedicationAdministrationStatusEnum.COMPLETED);

        medicationAdministration.getPatient().setReference("Patient/example");

        return medicationAdministration;
    }

    private void sendMedicationAdministration(MedicationAdministration medicationAdministration, MedicationArrAdapter medicationArrAdapter, MedicationModel medicationModel) {
        new ResourceTransfer(medicationArrAdapter, medicationModel).execute(medicationAdministration, client);
    }

    // SCTID 65384011000036101 in AMT is "Strepsils honey and lemon lozenge"
    // creates a FHIR MedicationAdministration resource with the taken medication at current time
    public void tookMedication(MedicationModel medicationModel, MedicationArrAdapter medicationArrAdapter) {

        // create a Medication
        Medication medication = createMedication(medicationModel.getSctid(), medicationModel.getName());

        // create an instance of a Medication being applied
        MedicationAdministration medicationAdministration = createMedicationAdministration();

        // Put the Medication as a contained reference in the MedicationAdministration resource
        medicationAdministration.getMedication().setResource(medication);

        // mail the resource off to the server. Hope our network is fine and all that.
        sendMedicationAdministration(medicationAdministration, medicationArrAdapter, medicationModel);
    }
}

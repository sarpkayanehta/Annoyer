package com.example.sarpkaya.annoyer;

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
    private static FhirContext ctx;
    private static String serverBase = "http://fhir-dev.healthintersections.com.au/open";
    private static IGenericClient client;

    public static FHIRInterface getInstance() {
        ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverBase);

        return ourInstance;
    }

    private FHIRInterface() {
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

    private void sendMedicationAdministration(MedicationAdministration medicationAdministration) {
        ArrayList<Object> list = new ArrayList<>();
        list.add((Object) medicationAdministration);
        list.add((Object) client);

        new ResourceTransfer().execute(list);
    }

    // SCTID 65384011000036101 in AMT is "Strepsils honey and lemon lozenge"
    // creates a FHIR MedicationAdministration resource with the taken medication at current time
    public void tookMedication(long sctid, String name) {

        // create a Medication
        Medication medication = createMedication(sctid, name);

        // create an instance of a Medication being applied
        MedicationAdministration medicationAdministration = createMedicationAdministration();

        // Put the Medication as a contained reference in the MedicationAdministration resource
        medicationAdministration.getMedication().setResource(medication);

        // mail the resource off to the server. Hope our network is fine and all that.
        sendMedicationAdministration(medicationAdministration);
    }
}

package com.example.sarpkaya.annoyer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationAdministration;
import ca.uhn.fhir.model.dstu2.valueset.MedicationAdministrationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import java.util.List;

/**
 * Created by VadimPeretokin on 25/03/15.
 */
public class FHIRInterface {
    private static FHIRInterface ourInstance = new FHIRInterface();

    public static FHIRInterface getInstance() {
        return ourInstance;
    }

    private FHIRInterface() {
    }

    // SCTID 65384011000036101 in AMT is "Strepsils honey and lemon lozenge"
    // creates a FHIR MedicationAdministration resource with the taken medication at current time
    public void tookMedication(long sctid, String name) {

        FhirContext ctx = FhirContext.forDstu2();

        // create a Medication
        Medication medication = new Medication();
        medication.setName(name);
        List<CodingDt> codedMedicine = new ArrayList<CodingDt>();
        // setSystem is wrong for v3, need to find the right URI
        //codedMedicine.add(new CodingDt().setDisplay(name).setCode(sctid.toString()).setSystem("http://nehta.gov.au/amt/v3"));
        medication.setCode(new CodeableConceptDt().setCoding(codedMedicine));


        // create an instance of a Medication being applied
        MedicationAdministration medicationAdministration = new MedicationAdministration();
        medicationAdministration.setEffectiveTime(new DateTimeDt().withCurrentTime()).setStatus(MedicationAdministrationStatusEnum.COMPLETED);

        // set the Medication taken to the Medication resource
        medicationAdministration.getMedication().setResource(medication);

        String jsonEncoded = ctx.newJsonParser().encodeResourceToString(medicationAdministration);
        Log.d("resource", jsonEncoded);
    }
}

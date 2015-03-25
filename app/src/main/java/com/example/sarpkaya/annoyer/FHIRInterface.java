package com.example.sarpkaya.annoyer;

import android.util.Log;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.MedicationAdministration;
import ca.uhn.fhir.model.dstu2.valueset.MedicationAdministrationStatusEnum;

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
    public void tookMedication(long sctid) {

        FhirContext ctx = FhirContext.forDstu2();

        MedicationAdministration medicationAdministration = new MedicationAdministration();

        medicationAdministration.setStatus(MedicationAdministrationStatusEnum.COMPLETED);

        String jsonEncoded = ctx.newJsonParser().encodeResourceToString(medicationAdministration);
        Log.d("resource", jsonEncoded);
    }
}

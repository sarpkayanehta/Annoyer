package com.example.sarpkaya.annoyer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.MedicationAdministration;
import ca.uhn.fhir.model.dstu2.valueset.MedicationAdministrationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;

/**
 * Created by VadimPeretokin on 25/03/15.
 */
public class PatientMedications {
    private static PatientMedications ourInstance = new PatientMedications();

    public List<MedicationModel> getMedicationModelList() {
        return medicationModelList;
    }

    private List<MedicationModel> medicationModelList;

    public static PatientMedications getInstance() {
        return ourInstance;
    }

    private PatientMedications() {
        populateMedications();
    }

    private void populateMedications() {
        medicationModelList = new ArrayList<>();

        //Medications
        MedicationModel medicationModel = new MedicationModel();
        medicationModel.setName("Strepsils honey and lemon lozenge");
        medicationModel.setSctid(65384011000036101L);
        DateTimeDt timeToTakeMedication = DateTimeDt.withCurrentTime();
        medicationModel.setMedicationTimeToBeTaken(timeToTakeMedication);

        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("A");
        medicationModel.setSctid(653840110000361L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());

        medicationModelList.add(medicationModel);
    }
}

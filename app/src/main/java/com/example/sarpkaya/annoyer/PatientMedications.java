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
        MedicationModel medicationModel = new MedicationModel();

        //Medications
        medicationModel = new MedicationModel();
        medicationModel.setName("memantine hydrochloride 20 mg tablet");
        medicationModel.setSctid(83436011000036100L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("econazole nitrate 1% (10 mg/g) cream");
        medicationModel.setSctid(61893011000036101L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("idarubicin hydrochloride 10 mg/10 mL injection, vial");
        medicationModel.setSctid(33681011000036108L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("dressing foam with silver 7.5 cm x 7.5 cm dressing");
        medicationModel.setSctid(933241091000036109L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("lamotrigine 100 mg tablet");
        medicationModel.setSctid(23610011000036100L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("temozolomide 5 mg capsule");
        medicationModel.setSctid(22983011000036102L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("aspirin 500 mg + codeine phosphate hemihydrate 8 mg tablet: dispersible");
        medicationModel.setSctid(61935011000036104L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("fluticasone propionate 500 microgram/actuation inhalation: powder for, actuation");
        medicationModel.setSctid(22752011000036107L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("potassium chloride 0.15% (750 mg/500 mL) + sodium chloride 0.225% (1.125 g/500 mL) + glucose 3.75% (18.75 g/500 mL) injection, bag");
        medicationModel.setSctid(70194011000036104L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);

        medicationModel = new MedicationModel();
        medicationModel.setName("moxonidine 200 microgram tablet");
        medicationModel.setSctid(22059011000036104L);
        medicationModel.setMedicationTimeToBeTaken(DateTimeDt.withCurrentTime());
        medicationModelList.add(medicationModel);


    }
}

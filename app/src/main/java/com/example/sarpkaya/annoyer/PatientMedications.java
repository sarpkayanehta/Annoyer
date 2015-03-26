package com.example.sarpkaya.annoyer;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.MedicationAdministration;
import ca.uhn.fhir.model.dstu2.valueset.MedicationAdministrationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;

/**
 * Created by VadimPeretokin on 25/03/15.
 */
public class PatientMedications {
    private static PatientMedications instance;
    private static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    private static final Random random = new Random();

    public List<MedicationModel> getMedicationModelList() {
        return medicationModelList;
    }

    private List<MedicationModel> medicationModelList;
    private Context context;

    public static PatientMedications getInstance(Context context) {
        if (instance == null) instance = new PatientMedications(context);
        return instance;
    }

    private PatientMedications(Context context) {
        this.context = context;
        populateMedications();
    }

    private void populateMedications() {
        medicationModelList = new ArrayList<>();

        medicationModelList.add(builder("memantine hydrochloride 20 mg tablet",
                83436011000036100L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("econazole nitrate 1% (10 mg/g) cream",
                61893011000036101L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("idarubicin hydrochloride 10 mg/10 mL injection, vial",
                33681011000036108L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("dressing foam with silver 7.5 cm x 7.5 cm dressing",
                933241091000036109L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("lamotrigine 100 mg tablet",
                23610011000036100L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("temozolomide 5 mg capsule",
                22983011000036102L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("aspirin 500 mg + codeine phosphate hemihydrate 8 mg tablet: dispersible",
                61935011000036104L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("fluticasone propionate 500 microgram/actuation inhalation: powder for, actuation",
                22752011000036107L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("potassium chloride 0.15% (750 mg/500 mL) + sodium chloride 0.225% (1.125 g/500 mL) + glucose 3.75% (18.75 g/500 mL) injection, bag",
                70194011000036104L, DateTimeDt.withCurrentTime()));

        medicationModelList.add(builder("moxonidine 200 microgram tablet", 22059011000036104L, DateTimeDt.withCurrentTime()));

    }



    private MedicationModel builder(String name, Long id, DateTimeDt time) {
        MedicationModel medicationModel = new MedicationModel();
        medicationModel.setName(name);
        medicationModel.setSctid(id);
        time.setValue(new Date(time.getValue().getTime() + (random.nextInt(12) * ONE_MINUTE_IN_MILLIS)));
        medicationModel.setMedicationTimeToBeTaken(time);
        new AlarmService(context, medicationModel).startAlarm();
        return medicationModel;
    }
}

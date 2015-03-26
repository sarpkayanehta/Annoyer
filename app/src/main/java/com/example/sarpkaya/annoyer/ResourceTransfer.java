package com.example.sarpkaya.annoyer;

import android.os.AsyncTask;
import android.view.View;

import java.util.ArrayList;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICreateTyped;

// this should really be just a two-value thing
public class ResourceTransfer extends AsyncTask<Object, Integer, MethodOutcome> {
    private MedicationArrAdapter medicationArrAdapter;
    private  MedicationModel medicationModel;

    public ResourceTransfer(MedicationArrAdapter medicationArrAdapter, MedicationModel medicationModel) {
        this.medicationArrAdapter = medicationArrAdapter;
        this.medicationModel = medicationModel;
    }

    @Override
    protected MethodOutcome doInBackground(Object... params) {
        ICreateTyped createTyped = ((IGenericClient) params[1]).create()
                .resource(((IResource) params[0]))
                .prettyPrint()
                .encodedJson();
        try {
            return createTyped.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(MethodOutcome result) {
        if (result != null) {
            IdDt id = result.getId();
            System.out.println("Got ID: " + id.getValue());
            medicationModel.setMedicationTaken(result.getCreated());
            medicationArrAdapter.notifyDataSetChanged();
        }
    }
}

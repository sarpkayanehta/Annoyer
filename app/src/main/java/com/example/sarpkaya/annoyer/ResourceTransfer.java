package com.example.sarpkaya.annoyer;

import android.os.AsyncTask;

import java.util.ArrayList;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;

// this should really be just a two-value thing
public class ResourceTransfer extends AsyncTask<Object, Integer, MethodOutcome> {
    @Override
    protected MethodOutcome doInBackground(Object... params) {
        MethodOutcome outcome = ((IGenericClient) params[1]).create()
                .resource(((IResource) params[0]))
                .prettyPrint()
                .encodedJson()
                .execute();
        return outcome;
    }

    @Override
    protected void onPostExecute(MethodOutcome result) {
        IdDt id = result.getId();
        System.out.println("Got ID: " + id.getValue());
    }
}

package com.example.sarpkaya.annoyer;

/**
 * Created by vadi on 25/03/15.
 */
public class FHIRInterface {
    private static FHIRInterface ourInstance = new FHIRInterface();

    public static FHIRInterface getInstance() {
        return ourInstance;
    }

    private FHIRInterface() {
    }
}

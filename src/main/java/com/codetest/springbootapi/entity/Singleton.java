package com.codetest.springbootapi.entity;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static volatile Singleton sSoleInstance;

    //private constructor.
    private Singleton() {

        //Prevent form the reflection api.
        if (sSoleInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Singleton getInstance() {
        if (sSoleInstance == null) { //if there is no instance available... create new one
            synchronized (Singleton.class) {
                if (sSoleInstance == null) sSoleInstance = new Singleton();
            }
        }

        return sSoleInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected Singleton readResolve() {
        return getInstance();
    }
}

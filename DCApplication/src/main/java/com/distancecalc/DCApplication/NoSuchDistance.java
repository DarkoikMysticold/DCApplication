package com.distancecalc.DCApplication;

public class NoSuchDistance extends RuntimeException {


    static final long serialVersionUID = -7034897190745766939L;

    NoSuchDistance(String message) {
        super(message);
    }
}

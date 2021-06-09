package com.distancecalc.DCApplication;

public class CityNotFound extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;

    CityNotFound(String message){
        super(message);
    }

}

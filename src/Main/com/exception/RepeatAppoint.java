package com.exception;

public class RepeatAppoint extends RuntimeException {
    public RepeatAppoint(String message){
        super(message);
    }
    public  RepeatAppoint(String message,Throwable cause){
        super(message,cause);
    }
}

package com.turkcell.product_service.core.exception.detail;

public class ExceptionDetails {
    private String message;
    public ExceptionDetails(){
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionDetails(String message){
        this.message = message;
    }
}

package com.zmarket.marketadminservice.exceptions;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}

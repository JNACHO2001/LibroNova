package com.mycompany.biblioteca.exeptions;

public class noExistentResourceException extends RuntimeException {

    public noExistentResourceException(String message) {
        super(message);
    }

}

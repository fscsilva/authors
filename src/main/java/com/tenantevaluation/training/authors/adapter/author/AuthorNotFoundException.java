package com.tenantevaluation.training.authors.adapter.author;

import jakarta.validation.ValidationException;

public class AuthorNotFoundException extends ValidationException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}

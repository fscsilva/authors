package com.tenantevaluation.training.authors.adapter.author;

import jakarta.validation.ValidationException;

public class MovieException extends ValidationException {

    public MovieException(String message) {
        super(message);
    }
}

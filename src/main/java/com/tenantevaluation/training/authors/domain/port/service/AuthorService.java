package com.tenantevaluation.training.authors.domain.port.service;

import com.tenantevaluation.training.authors.domain.author.Author;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AuthorService {

    CompletableFuture<Author> getAuthor(BigInteger id);

    CompletableFuture<Author> addAuthor(Author author);

    CompletableFuture<List<Author>>  getAuthorsByName(String name, int page, int size);

}

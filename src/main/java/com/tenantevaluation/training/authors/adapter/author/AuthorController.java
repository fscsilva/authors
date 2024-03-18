package com.tenantevaluation.training.authors.adapter.author;

import com.tenantevaluation.training.authors.domain.author.Author;
import com.tenantevaluation.training.authors.domain.port.api.AuthorAPI;
import com.tenantevaluation.training.authors.domain.port.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController implements AuthorAPI {

    private final AuthorService authorService;

    @Override
    public ResponseEntity<Author> addAuthor(Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(author).join());
    }

    @Override
    public ResponseEntity<Author> getAuthor(BigInteger id) {
        return ResponseEntity.ok(authorService.getAuthor(id).join());
    }

    @Override
    public ResponseEntity<List<Author>> getAuthorsByName(String name, int page, int size) {
        return ResponseEntity.ok(authorService.getAuthorsByName(name, page, size).join());
    }
}

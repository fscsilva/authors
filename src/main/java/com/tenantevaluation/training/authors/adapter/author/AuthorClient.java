package com.tenantevaluation.training.authors.adapter.author;

import com.tenantevaluation.training.authors.adapter.persistence.model.AuthorEntity;
import com.tenantevaluation.training.authors.adapter.persistence.repository.AuthorRepository;
import com.tenantevaluation.training.authors.domain.author.Author;
import com.tenantevaluation.training.authors.domain.port.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorClient implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public CompletableFuture<Author> getAuthor(BigInteger id) {
        return CompletableFuture.supplyAsync(() ->  authorRepository.findById(id))
            .thenApply(entity -> entity.orElseThrow(() -> new AuthorNotFoundException("author not found")))
            .thenApply(authorEntity -> Author.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLast_name())
                .birthPlace(authorEntity.getBirth_place())
                .birthDate(authorEntity.getBirth_date().toLocalDate())
                .build());
    }

    @Override
    public CompletableFuture<Author> addAuthor(Author author) {
        var entity = new AuthorEntity();
        entity.setBirth_date(Date.valueOf(author.getBirthDate()));
        entity.setBirth_place(author.getBirthPlace());
        entity.setFirstName(author.getFirstName());
        entity.setLast_name(author.getLastName());

        return CompletableFuture.supplyAsync(() ->  authorRepository.save(entity))
            .thenApply(authorEntity -> Author.builder()
                .birthDate(authorEntity.getBirth_date().toLocalDate())
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLast_name())
                .birthPlace(authorEntity.getBirth_place())
                .build());
    }

    @Override
    public CompletableFuture<List<Author>> getAuthorsByName(String name, int page, int size) {
        return CompletableFuture.supplyAsync(() -> authorRepository.findAllByFirstNameContainingIgnoreCase(name, PageRequest.of(page, size))
            .stream().map(authorEntity -> Author.builder()
                .id(authorEntity.getId())
                .birthDate(authorEntity.getBirth_date().toLocalDate())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLast_name())
                .birthPlace(authorEntity.getBirth_place())
                .build())
            .toList());
    }
}
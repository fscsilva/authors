package com.tenantevaluation.training.authors.adapter.persistence.repository;


import com.tenantevaluation.training.authors.adapter.persistence.model.AuthorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "author", path = "authors")
public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, BigInteger>, CrudRepository<AuthorEntity, BigInteger> {

    List<AuthorEntity> findAllByFirstNameContainingIgnoreCase(@Param("first_name") String first_name, Pageable pageable);
}

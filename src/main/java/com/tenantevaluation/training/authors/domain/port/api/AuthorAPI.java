package com.tenantevaluation.training.authors.domain.port.api;


import com.tenantevaluation.training.authors.domain.author.Author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("v1/authors")
public interface AuthorAPI {

    @Operation(summary = "Creates a Author")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Author created",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)) }),
        @ApiResponse(responseCode = "500", description = "There was an error trying to create a Author"),
        @ApiResponse(responseCode = "400", description = "Malformed request")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Author> addAuthor(@RequestBody Author Author);
    
    @Operation(summary = "Get a Author")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Author retrieved",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)) }),
        @ApiResponse(responseCode = "500", description = "There was an error trying to Get a Author"),
        @ApiResponse(responseCode = "400", description = "Malformed request")})
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Author> getAuthor(@PathVariable BigInteger id);

    @Operation(summary = "Get a Authors by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Authors retrieved",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Author.class))) }),
        @ApiResponse(responseCode = "500", description = "There was an error trying to Get a Authors by name"),
        @ApiResponse(responseCode = "400", description = "Malformed request")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Author>> getAuthorsByName(@RequestParam("name") String name, @RequestParam("page") int page,
        @RequestParam("size") int size);

}

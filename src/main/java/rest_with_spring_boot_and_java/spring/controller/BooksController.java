package rest_with_spring_boot_and_java.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest_with_spring_boot_and_java.spring.service.BooksService;
import rest_with_spring_boot_and_java.spring.util.MediaType;
import rest_with_spring_boot_and_java.spring.vo.BookVO;
import java.util.List;

@RestController
@RequestMapping("api/book/v1")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {

    @Autowired
    BooksService service;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML})
    @Operation(summary = "Find a book"
            , description = "Find a book"
            , tags = {"Books"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content =
            @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No content"
                    , responseCode = "204"
                    , content = @Content),
            @ApiResponse(description = "Bad Request"
                    , responseCode = "400"
                    , content = @Content),
            @ApiResponse(description = "Unauthorized"
                    , responseCode = "401"
                    , content = @Content),
            @ApiResponse(description = "Not Found"
                    , responseCode = "404"
                    , content = @Content),
            @ApiResponse(description = "Internal Server Error"
                    , responseCode = "500"
                    , content = @Content)
    })
    public BookVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML})

    @Operation(summary = "Find all books"
            , description = "Find all books"
            , tags = {"Books"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content ={
                    @Content(mediaType = "application/json"
                            ,array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))}),
            @ApiResponse(description = "Bad Request"
                    , responseCode = "400"
                    , content = @Content),
            @ApiResponse(description = "Unauthorized"
                    , responseCode = "401"
                    , content = @Content),
            @ApiResponse(description = "Not Found"
                    , responseCode = "404"
                    , content = @Content),
            @ApiResponse(description = "Internal Server Error"
                    , responseCode = "500"
                    , content = @Content)
    })
    public List<BookVO> findAll()  {

        return service.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML},
            consumes = {MediaType.APPLICATION_JSON
                    , MediaType.APPLICATION_XML
                    , MediaType.APPLICATION_YAML})

    @Operation(summary = "Add a new book"
            , description = "Add a new book"
            , tags = {"Books"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content =
            @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request"
                    , responseCode = "400"
                    , content = @Content),
            @ApiResponse(description = "Unauthorized"
                    , responseCode = "401"
                    , content = @Content),
            @ApiResponse(description = "Internal Server Error"
                    , responseCode = "500"
                    , content = @Content)
    })
    public BookVO create(@RequestBody BookVO book) {

        return service.create(book);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML},
            consumes = {MediaType.APPLICATION_JSON
                    , MediaType.APPLICATION_XML
                    , MediaType.APPLICATION_YAML})
    @Operation(summary = "Update a book"
            , description = "Update a book"
            , tags = {"Books"}
            , responses = {
            @ApiResponse(description = "Updated"
                    , responseCode = "200"
                    , content =
            @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request"
                    , responseCode = "400"
                    , content = @Content),
            @ApiResponse(description = "Unauthorized"
                    , responseCode = "401"
                    , content = @Content),
            @ApiResponse(description = "Not Found"
                    , responseCode = "404"
                    , content = @Content),
            @ApiResponse(description = "Internal Server Error"
                    , responseCode = "500"
                    , content = @Content)
    })
    public BookVO update(@RequestBody BookVO book) {

        return service.update(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book"
            , description = "Delete a ok"
            , tags = {"Books"}
            , responses = {
            @ApiResponse(description = "No content"
                    , responseCode = "204"
                    , content =@Content),
            @ApiResponse(description = "Bad Request"
                    , responseCode = "400"
                    , content = @Content),
            @ApiResponse(description = "Unauthorized"
                    , responseCode = "401"
                    , content = @Content),
            @ApiResponse(description = "Not Found"
                    , responseCode = "404"
                    , content = @Content),
            @ApiResponse(description = "Internal Server Error"
                    , responseCode = "500"
                    , content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}

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
import rest_with_spring_boot_and_java.spring.service.PersonService;
import rest_with_spring_boot_and_java.spring.util.MediaType;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML})
    @Operation(summary = "Find a person"
            , description = "Find a person"
            , tags = {"People"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content =
                    @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML})

    @Operation(summary = "Find all people"
            , description = "Find all people"
            , tags = {"People"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content ={
                    @Content(mediaType = "application/json"
                            ,array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))}),
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
    public List<PersonVO> findAll()  {

        return service.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML},
            consumes = {MediaType.APPLICATION_JSON
                    , MediaType.APPLICATION_XML
                    , MediaType.APPLICATION_YAML})

    @Operation(summary = "Add a new person"
            , description = "Finds a person"
            , tags = {"People"}
            , responses = {
            @ApiResponse(description = "Success"
                    , responseCode = "200"
                    , content =
            @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO create(@RequestBody PersonVO PersonVo) {

        return service.create(PersonVo);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.APPLICATION_YAML},
                consumes = {MediaType.APPLICATION_JSON
                    , MediaType.APPLICATION_XML
                    , MediaType.APPLICATION_YAML})
    @Operation(summary = "Update a person"
            , description = "Update a person"
            , tags = {"People"}
            , responses = {
            @ApiResponse(description = "Updated"
                    , responseCode = "200"
                    , content =
            @Content(schema = @Schema(implementation = PersonVO.class))),
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
    public PersonVO update(@RequestBody PersonVO PersonVo) {

        return service.update(PersonVo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person"
            , description = "Delete a person"
            , tags = {"People"}
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

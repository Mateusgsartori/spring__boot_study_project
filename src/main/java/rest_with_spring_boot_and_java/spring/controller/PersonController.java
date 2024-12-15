package rest_with_spring_boot_and_java.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest_with_spring_boot_and_java.spring.service.PersonService;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }


    @GetMapping
    public List<PersonVO> findAll()  {

        return service.findAll();
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO PersonVo) {

        return service.create(PersonVo);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO PersonVo) {

        return service.update(PersonVo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

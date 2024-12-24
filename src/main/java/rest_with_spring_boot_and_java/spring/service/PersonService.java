package rest_with_spring_boot_and_java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import rest_with_spring_boot_and_java.spring.controller.PersonController;
import rest_with_spring_boot_and_java.spring.exceptions.RequiredObjectIsNullException;
import rest_with_spring_boot_and_java.spring.exceptions.ResourceNotFoundExeption;
import rest_with_spring_boot_and_java.spring.mapper.PersonMapper;
import rest_with_spring_boot_and_java.spring.repository.PersonRepository;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Finding all people");

        var people =  PersonMapper.mapToVOList(repository.findAll());

        people.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return people;

    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVo");
        var vo = repository.findById(id)
                .map(PersonMapper::mapToVO)
                .orElseThrow(() -> new ResourceNotFoundExeption("Person not found!"));

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;

    }

    public PersonVO create(PersonVO person) {
        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating PersonVo");

        var entity = PersonMapper.mapToEntity(person);
        var vo = PersonMapper.mapToVO(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO vo) {
        if (vo == null) {
            throw new RequiredObjectIsNullException();
        }
        logger.info("Updating PersonVo");
        var entity = repository.findById(vo.getKey()).orElseThrow(() -> new ResourceNotFoundExeption("No record found for this ID!"));

        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());

        repository.save(entity);
        var person = PersonMapper.mapToVO(repository.save(entity));

        person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return person;
    }

    public void delete(Long id) {
        logger.info("Deleting PersonVo");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("No record found for this ID!"));
        repository.delete(entity);

    }

}

package rest_with_spring_boot_and_java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest_with_spring_boot_and_java.spring.exceptions.ResourceNotFoundEception;
import rest_with_spring_boot_and_java.spring.mapper.Mapper;
import rest_with_spring_boot_and_java.spring.model.Person;
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
        return Mapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVo");
        return repository.findById(id).map(person -> Mapper.parseObject(person, PersonVO.class)).orElseThrow(() -> new ResourceNotFoundEception("Person not found!"));
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating PersonVo");

        var entity = Mapper.parseObject(person, Person.class);

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO vo) {
        logger.info("Updating PersonVo");
        var entity = repository.findById(vo.getId()).orElseThrow(() -> new ResourceNotFoundEception("No record found for this ID!"));

        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());

        repository.save(entity);

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting PersonVo");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundEception("No record found for this ID!"));
        repository.delete(entity);

    }

}

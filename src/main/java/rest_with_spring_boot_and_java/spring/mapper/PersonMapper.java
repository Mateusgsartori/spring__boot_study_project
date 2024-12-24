package rest_with_spring_boot_and_java.spring.mapper;

import rest_with_spring_boot_and_java.spring.model.Person;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;

public class PersonMapper {

    public static PersonVO mapToVO(Person person) {
        PersonVO vo = Mapper.parseObject(person, PersonVO.class);
        vo.setKey(person.getId());
        return vo;
    }

    public static Person mapToEntity(PersonVO personVO) {
        Person person = Mapper.parseObject(personVO, Person.class);
        person.setId(personVO.getKey());
        return person;
    }

    public static List<PersonVO> mapToVOList(List<Person> personList) {
        List<PersonVO> personVOList = Mapper.parseListObjects(personList, PersonVO.class);
        for (int i = 0; i < personList.size(); i++) {
            personVOList.get(i).setKey(personList.get(i).getId());
        }

        return personVOList;
    }

    public static List<Person> mapToEntityList(List<PersonVO> personVOList) {
        List<Person> personList = Mapper.parseListObjects(personVOList, Person.class);

        for (int i = 0; i < personVOList.size(); i++) {
            personList.get(i).setId(personVOList.get(i).getKey());
        }

        return personList;

    }

}

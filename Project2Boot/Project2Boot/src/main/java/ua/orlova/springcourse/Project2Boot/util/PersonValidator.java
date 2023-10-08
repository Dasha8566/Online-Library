package ua.orlova.springcourse.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.orlova.springcourse.Project2Boot.models.Person;
import ua.orlova.springcourse.Project2Boot.repositories.PeopleRepository;


@Component
public class PersonValidator implements Validator {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
Person person=(Person) o;
if(peopleRepository.getByFullName(person.getFullName()).isPresent()){
    errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
}
    }
}

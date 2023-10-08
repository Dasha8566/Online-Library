package ua.orlova.springcourse.Project2Boot.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.orlova.springcourse.Project2Boot.models.Book;
import ua.orlova.springcourse.Project2Boot.models.Person;
import ua.orlova.springcourse.Project2Boot.repositories.BooksRepository;
import ua.orlova.springcourse.Project2Boot.repositories.PeopleRepository;


import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

public List<Person> findAll(){
        return peopleRepository.findAll();
}

    public Person findOne(int id){
        Optional<Person> foundPerson=peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }


    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public  List<Book> getBooksByPersonId(int id){
   Optional <Person> person=peopleRepository.findById(id);

   if(person.isPresent()){
       Hibernate.initialize(person.get().getBooks());
       List<Book> books=new ArrayList<>(person.get().getBooks());
       if(!books.isEmpty()){
       for(Book book:books){
           if((new Date().getTime()-book.getTookTime().getTime())> TimeUnit.DAYS.toMillis(10)){
               book.setExpired(true);
           }
       }
       }
       return person.get().getBooks();
   }else{
       return Collections.emptyList();
   }
    }

}

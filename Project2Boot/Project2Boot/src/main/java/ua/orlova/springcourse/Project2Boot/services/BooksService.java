package ua.orlova.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.orlova.springcourse.Project2Boot.models.Book;
import ua.orlova.springcourse.Project2Boot.models.Person;
import ua.orlova.springcourse.Project2Boot.repositories.BooksRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public List<Book> findAll(int page, int books_per_page){
        return booksRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }

    public List<Book> findAll(boolean bool){
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAll(int page, int books_per_page, boolean bool){
        return booksRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("year"))).getContent();
    }


    public Book findOne(int id){
        Optional<Book> foundBook=booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id){
        Optional<Book> book=booksRepository.findById(id);
            return Optional.ofNullable(book.get().getOwner());

    }

    @Transactional
    public void release(int id){
        Optional<Book> book=booksRepository.findById(id);
        book.get().setOwner(null);
        book.get().setTookTime(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        Optional<Book> book=booksRepository.findById(id);
        book.get().setOwner(selectedPerson);
        book.get().setTookTime(new Date());
    }

    public Optional<Book> search(String startingWith){
       return booksRepository.findByTitleStartingWith(startingWith);
    }


}

package ua.orlova.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.orlova.springcourse.Project2Boot.models.Book;


import java.util.Optional;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
Optional<Book> findByTitleStartingWith(String startingWith);
}

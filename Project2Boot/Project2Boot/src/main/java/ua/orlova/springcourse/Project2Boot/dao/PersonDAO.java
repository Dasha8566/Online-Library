package ua.orlova.springcourse.Project2Boot.dao;

import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index(){
//return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//
//    }
//
//    public Person show(int id){
//return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//        .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person){
//jdbcTemplate.update("INSERT INTO Person (full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
//    }
//
//    public void update(int id, Person updatePerson){
//   jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?", updatePerson.getFullName(), updatePerson.getYearOfBirth(), id);
//    }
//
//    public void delete(int id){
//  jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//public Optional<Person> getPersonByFullName(String fullName){
//        return jdbcTemplate.query("SELECT * FROM Person where full_name=?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//}
//
//public  List<Book> getBooksByPersonId(int id){
//        return jdbcTemplate.query("SELECT * FROM Book where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//}
//


}

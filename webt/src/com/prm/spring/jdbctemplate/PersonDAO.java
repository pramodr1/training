package com.prm.spring.jdbctemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createPerson(Person person) {
        String sql = "INSERT INTO person (id, name, age) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, person.getId(), person.getName(), person.getAge());
    }

    public Person getPersonById(int id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Person> getAllPersons() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class));
    }

    public void updatePerson(Person person) {
        String sql = "UPDATE person SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getId());
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
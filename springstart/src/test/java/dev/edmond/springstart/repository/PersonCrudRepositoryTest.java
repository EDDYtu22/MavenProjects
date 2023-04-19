package dev.edmond.springstart.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import dev.edmond.springstart.models.Person;

@DataJpaTest
//@RunWith(SpringRunner.class)
public class PersonCrudRepositoryTest {
    

    @Autowired
    private PersonRepository repo;

    @Autowired
    private TestEntityManager em;


    // @Test
    // @Disabled
    // @Sql("classpath:db/singlePerson.sql")
    // void shouldFetchPersonByIdFromDb(){
    //     String existingUUID = "8155db1e-3352-4abb-af78-0cf20a04b851";

    //     Optional<Person> person = repo.findById(UUID.fromString(existingUUID));

    //     assertTrue(person.isPresent());
    // }


    @Test
    void shouldFetchPersonByIdPerssistedViaEM(){

        Person person = Person.builder()
        .name("Teodor")
        .age(34)
        .egnNumber("0123456789")
        .build();

        Person perssistedPerson = em.persist(person);

        assertTrue(repo.findById(perssistedPerson.getId()).isPresent());
    }

    // @Test
    // void shouldExistPersonByEgnNumber(){

    //     String egnNumber = "0123456789";
    //     String invalidEgnNumber = "9090";

    //     Person person = Person.builder()
    //     .name("Teodor")
    //     .age(34)
    //     .egnNumber(egnNumber)
    //     .build();

    //     Person perssistedPerson = em.persist(person);

    //     assertTrue(repo.existByEgnNumber(egnNumber));
    //     assertFalse(repo.existByEgnNumber(invalidEgnNumber));
    // }
}

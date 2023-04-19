package dev.edmond.springstart.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.edmond.springstart.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {

    @Query(
        """
        SELECT CASE WHEN COUNT(p) > 0 THEN
        TRUE ELSE FALSE END
        FROM PERSON p 
        WHERE p.egnNumber = ?1        
        """
    )
    boolean existByEgnNumber(String egnNumber);

}

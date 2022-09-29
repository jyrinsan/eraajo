package fi.tutkimusprosessi.eraajo.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("personRepository")
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

}

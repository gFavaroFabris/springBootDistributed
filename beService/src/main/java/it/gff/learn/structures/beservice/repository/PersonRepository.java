package it.gff.learn.structures.beservice.repository;

import it.gff.learn.structures.beservice.accessingdatarest.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person,Long> {

    default List<Person> findByLastName(@Param("name") String name) {
        return List.of(Person.of(1, name, name));
    }

    default List<Person> getPersonByFirstName() {
        return List.of(Person.of(1, "name", "ame"));
    }

}

package ru.clevertec.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.house.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = """
            select
            	p.*
            from
            	people p
            join house_history hh on
            	p.id = hh.person_id
            join houses h on
            	hh.house_id = h.id
            where
            	h.uuid = :houseId
            	and hh.type = 'TENANT'
            """, nativeQuery = true)
    List<Person> findAllPreviousResidentsOfHouse(UUID houseId);

    @Query(value = """
            select
            	p.*
            from
            	people p
            join house_history hh on
            	p.id = hh.person_id
            join houses h on
            	hh.house_id = h.id
            where
            	h.uuid = :houseId
            	and hh.type = 'OWNER'
            """, nativeQuery = true)
    List<Person> findAllPreviousOwnersOfHouse(UUID houseId);

    Optional<Person> findByUuid(UUID id);

    List<Person> findPersonsByResidency_Uuid(UUID uuid);

    void deleteByUuid(UUID id);

}

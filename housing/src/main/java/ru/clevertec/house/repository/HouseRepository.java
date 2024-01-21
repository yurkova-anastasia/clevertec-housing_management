package ru.clevertec.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.house.model.House;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, Long> {

    @Query(value = """
            SELECT 
                h.*
            from
            	houses h
            join house_history hh on
            	h.id = hh.house_id
            join people p on
            	hh.person_id = p.id
            where
            	p.uuid = :personId
            	and hh.type = 'TENANT'
            """, nativeQuery = true)
    List<House> findAllPreviousResidencyOfPerson(UUID personId);

    @Query(value = """
            SELECT  
                h.*
            from
            	houses h
            join house_history hh on
            	h.id = hh.house_id
            join people p on
            	hh.person_id = p.id
            where
            	p.uuid = :personId
            	and hh.type = 'OWNER'
            """, nativeQuery = true)
    List<House> findAllPreviousOwnedHousesOfPerson(UUID personId);

    Optional<House> findByUuid(UUID uuid);

    List<House> findHousesByOwners_Uuid(UUID uuid);

    void deleteByUuid(UUID uuid);

}

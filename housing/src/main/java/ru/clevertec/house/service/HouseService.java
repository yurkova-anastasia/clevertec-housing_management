package ru.clevertec.house.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.house.model.House;

import java.util.List;
import java.util.UUID;

public interface HouseService {

    House create(House house);

    House findById(UUID id);

    List<House> findHousesByOwner(UUID id);

    Page<House> findAll(Pageable pageable);

    List<House> findAllPreviousResidencyOfPerson(UUID personId);

    List<House> findAllPreviousOwnedHousesOfPerson(UUID personId);

    House update(UUID id, House house);

    void deleteById(UUID id);
}

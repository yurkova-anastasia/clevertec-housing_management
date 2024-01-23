package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cachestarter.annotation.CustomCacheable;
import ru.clevertec.house.exception.ServiceException;
import ru.clevertec.house.model.House;
import ru.clevertec.house.repository.HouseRepository;
import ru.clevertec.house.service.HouseService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    public House create(House house) {
        return houseRepository.save(house);
    }

    @Override
    @CustomCacheable
    public House findById(UUID id) {
        return getById(id);
    }

    @Override
    public Page<House> findHousesByOwner(UUID id, Pageable pageable) {
        return houseRepository.findHousesByOwners_Uuid(id, pageable);
    }

    @Override
    @CustomCacheable
    public Page<House> findAll(Pageable pageable) {
        return houseRepository.findAll(pageable);
    }

    @Override
    public Page<House> findAllPreviousResidencyOfPerson(UUID personId, Pageable pageable) {
        return houseRepository.findAllPreviousResidencyOfPerson(personId, pageable);
    }

    @Override
    public Page<House> findAllPreviousOwnedHousesOfPerson(UUID personId, Pageable pageable) {
        return houseRepository.findAllPreviousOwnedHousesOfPerson(personId, pageable);
    }

    @Override
    @Transactional
    @CustomCacheable
    public House update(UUID id, House house) {
        House existingHouse = getById(id);
        updateHouse(existingHouse, house);
        houseRepository.save(existingHouse);
        return existingHouse;
    }

    @Override
    @Transactional
    @CustomCacheable
    public void deleteById(UUID id) {
        houseRepository.deleteByUuid(id);
    }

    private void updateHouse(House existingHouse, House house) {
        if (house.getArea() != null) {
            existingHouse.setArea(house.getArea());
        }
        if (house.getCity() != null) {
            existingHouse.setCity(house.getCity());
        }
        if (house.getCountry() != null) {
            existingHouse.setCountry(house.getCountry());
        }
        if (house.getStreet() != null) {
            existingHouse.setStreet(house.getStreet());
        }
        if (house.getNumber() != null) {
            existingHouse.setNumber(house.getNumber());
        }
    }

    private House getById(UUID id) {
        return houseRepository.findByUuid(id)
                .orElseThrow(() -> new ServiceException("House was not found"));
    }
}

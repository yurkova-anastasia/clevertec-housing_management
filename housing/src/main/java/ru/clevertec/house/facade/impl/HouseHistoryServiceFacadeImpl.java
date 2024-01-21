package ru.clevertec.house.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.house.dto.response.HouseResponseDto;
import ru.clevertec.house.dto.response.PersonResponseDto;
import ru.clevertec.house.facade.HouseHistoryServiceFacade;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.HouseService;
import ru.clevertec.house.service.PersonService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseHistoryServiceFacadeImpl implements HouseHistoryServiceFacade {

    private final HouseService houseService;
    private final PersonService personService;
    private final HouseMapper houseMapper;
    private final PersonMapper personMapper;

    @Override
    public List<PersonResponseDto> findAllPreviousResidentsOfHouse(UUID houseId) {
        return personService.findAllPreviousResidentsOfHouse(houseId).stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Override
    public List<PersonResponseDto> findAllPreviousOwnersOfHouse(UUID houseId) {
        return personService.findAllPreviousOwnersOfHouse(houseId).stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Override
    public List<HouseResponseDto> findAllPreviousResidencyOfPerson(UUID personId) {
        return houseService.findAllPreviousResidencyOfPerson(personId).stream()
                .map(houseMapper::toDto)
                .toList();
    }

    @Override
    public List<HouseResponseDto> findAllPreviousOwnedHousesOfPerson(UUID personId) {
        return houseService.findAllPreviousOwnedHousesOfPerson(personId).stream()
                .map(houseMapper::toDto)
                .toList();
    }
}

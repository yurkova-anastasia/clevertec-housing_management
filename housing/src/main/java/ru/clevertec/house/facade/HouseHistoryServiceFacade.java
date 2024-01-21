package ru.clevertec.house.facade;

import ru.clevertec.house.dto.response.HouseResponseDto;
import ru.clevertec.house.dto.response.PersonResponseDto;

import java.util.List;
import java.util.UUID;

public interface HouseHistoryServiceFacade {

    List<PersonResponseDto> findAllPreviousResidentsOfHouse(UUID houseId);

    List<PersonResponseDto> findAllPreviousOwnersOfHouse(UUID houseId);

    List<HouseResponseDto> findAllPreviousResidencyOfPerson(UUID personId);

    List<HouseResponseDto> findAllPreviousOwnedHousesOfPerson(UUID personId);

}

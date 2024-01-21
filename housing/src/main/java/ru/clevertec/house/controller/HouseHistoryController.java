package ru.clevertec.house.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.dto.response.HouseResponseDto;
import ru.clevertec.house.dto.response.PersonResponseDto;
import ru.clevertec.house.facade.HouseHistoryServiceFacade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/house-history")
@RequiredArgsConstructor
@Tag(name = "House history controller", description = "The House history API")
public class HouseHistoryController {

    private final HouseHistoryServiceFacade houseHistoryServiceFacade;

    @GetMapping("{houseId}/previous-residents")
    public ResponseEntity<List<PersonResponseDto>> findAllPreviousResidentsOfHouse(@PathVariable("houseId") UUID houseId) {
        return new ResponseEntity<>(houseHistoryServiceFacade.findAllPreviousResidentsOfHouse(houseId), HttpStatus.OK);
    }

    @GetMapping("{houseId}/previous-owners")
    public ResponseEntity<List<PersonResponseDto>> findAllPreviousOwnersOfHouse(@PathVariable("houseId") UUID houseId) {
        return new ResponseEntity<>(houseHistoryServiceFacade.findAllPreviousOwnersOfHouse(houseId), HttpStatus.OK);
    }

    @GetMapping("{personId}/previous-residency")
    public ResponseEntity<List<HouseResponseDto>> findAllPreviousResidencyOfPerson(@PathVariable("personId") UUID personId) {
        return new ResponseEntity<>(houseHistoryServiceFacade.findAllPreviousResidencyOfPerson(personId), HttpStatus.OK);
    }

    @GetMapping("{personId}/previous-owned-houses")
    public ResponseEntity<List<HouseResponseDto>> findAllPreviousOwnedHousesOfPerson(@PathVariable("personId") UUID personId) {
        return new ResponseEntity<>(houseHistoryServiceFacade.findAllPreviousOwnedHousesOfPerson(personId), HttpStatus.OK);
    }


}

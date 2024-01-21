package ru.clevertec.house.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.dto.request.PersonRequestDto;
import ru.clevertec.house.dto.response.HouseResponseDto;
import ru.clevertec.house.dto.response.PersonResponseDto;
import ru.clevertec.house.facade.PersonServiceFacade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
@Tag(name = "Person controller", description = "The Person API")
public class PersonController {

    private final PersonServiceFacade personServiceFacade;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody PersonRequestDto personRequestDto) {
        personServiceFacade.create(personRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(personServiceFacade.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/owned-houses")
    public ResponseEntity<List<HouseResponseDto>> findOwnedHouses(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(personServiceFacade.findOwnedHouses(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PersonResponseDto>> findAll(@PageableDefault(size = 15) Pageable pageable) {
        return new ResponseEntity<>(personServiceFacade.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") UUID id,
                                             @RequestBody PersonRequestDto personRequestDto) {
        personServiceFacade.update(id, personRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}/owned-houses")
    public ResponseEntity<HttpStatus> addOwnedHouse(@PathVariable("id") UUID id,
                                                    @RequestParam UUID houseId) {
        personServiceFacade.addOwnedHouse(id, houseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}/-owned-houses")
    public ResponseEntity<HttpStatus> removeOwnedHouse(@PathVariable("id") UUID id,
                                                       @RequestParam UUID houseId) {
        personServiceFacade.removeOwnedHouse(id, houseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
        personServiceFacade.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package ru.clevertec.house.mapper;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.clevertec.house.dto.request.HouseRequestDto;
import ru.clevertec.house.dto.response.HouseResponseDto;
import ru.clevertec.house.model.House;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T14:41:52+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class HouseMapperImpl implements HouseMapper {

    @Override
    public HouseResponseDto toDto(House house) {
        if ( house == null ) {
            return null;
        }

        UUID uuid = null;
        String area = null;
        String country = null;
        String city = null;
        String street = null;
        String number = null;
        LocalDateTime createDate = null;
        LocalDateTime updateDate = null;

        uuid = house.getUuid();
        area = house.getArea();
        country = house.getCountry();
        city = house.getCity();
        street = house.getStreet();
        number = house.getNumber();
        createDate = house.getCreateDate();
        updateDate = house.getUpdateDate();

        HouseResponseDto houseResponseDto = new HouseResponseDto( uuid, area, country, city, street, number, createDate, updateDate );

        return houseResponseDto;
    }

    @Override
    public House toEntity(HouseRequestDto houseRequestDto) {
        if ( houseRequestDto == null ) {
            return null;
        }

        House house = new House();

        house.setArea( houseRequestDto.getArea() );
        house.setCountry( houseRequestDto.getCountry() );
        house.setCity( houseRequestDto.getCity() );
        house.setStreet( houseRequestDto.getStreet() );
        house.setNumber( houseRequestDto.getNumber() );

        setDates( house );

        return house;
    }
}

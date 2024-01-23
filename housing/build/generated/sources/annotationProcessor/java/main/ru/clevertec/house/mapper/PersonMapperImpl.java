package ru.clevertec.house.mapper;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.clevertec.house.dto.request.PersonRequestDto;
import ru.clevertec.house.dto.response.PersonResponseDto;
import ru.clevertec.house.model.Person;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T14:41:51+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl extends PersonMapper {

    @Override
    public PersonResponseDto toDto(Person person) {
        if ( person == null ) {
            return null;
        }

        UUID uuid = null;
        String name = null;
        String surname = null;
        String sex = null;
        String passportSeries = null;
        String passportNumber = null;
        LocalDateTime createDate = null;
        LocalDateTime updateDate = null;

        uuid = person.getUuid();
        name = person.getName();
        surname = person.getSurname();
        sex = person.getSex();
        passportSeries = person.getPassportSeries();
        passportNumber = person.getPassportNumber();
        createDate = person.getCreateDate();
        updateDate = person.getUpdateDate();

        PersonResponseDto personResponseDto = new PersonResponseDto( uuid, name, surname, sex, passportSeries, passportNumber, createDate, updateDate );

        return personResponseDto;
    }

    @Override
    public Person toEntity(PersonRequestDto personRequestDto) {
        if ( personRequestDto == null ) {
            return null;
        }

        Person person = new Person();

        try {
            person.setResidency( mapResidencyIdToHouse( personRequestDto.getResidencyId() ) );
        }
        catch ( EntityNotFoundException e ) {
            throw new RuntimeException( e );
        }
        person.setName( personRequestDto.getName() );
        person.setSurname( personRequestDto.getSurname() );
        person.setSex( personRequestDto.getSex() );
        person.setPassportSeries( personRequestDto.getPassportSeries() );
        person.setPassportNumber( personRequestDto.getPassportNumber() );

        setDates( person );

        return person;
    }
}

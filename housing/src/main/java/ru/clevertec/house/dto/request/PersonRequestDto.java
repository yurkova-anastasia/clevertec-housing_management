package ru.clevertec.house.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDto {

    private String name;
    private String surname;

    //    @Pattern(regexp = "^(Male|Female)$", message = "Sex must be Male or Female")
    private String sex;
    private String passportSeries;
    private String passportNumber;
    private UUID residencyId;

}

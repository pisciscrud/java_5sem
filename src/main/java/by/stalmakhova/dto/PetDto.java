package by.stalmakhova.dto;


import by.stalmakhova.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

    private Integer age;
    private PetTypeDto pet;


}

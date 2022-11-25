package by.stalmakhova.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private Long idOwner = null;
    private Integer age;
    private PetTypeDto pet;
    private String nickname;

}

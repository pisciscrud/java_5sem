package by.stalmakhova.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetTypeDto {
    private  Long id;
    private String petName;
}

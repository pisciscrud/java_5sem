package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetOut {
    private Long id;
    private Integer age;
    private String nickname;
    private String petName;
    private Long idOwner;

}

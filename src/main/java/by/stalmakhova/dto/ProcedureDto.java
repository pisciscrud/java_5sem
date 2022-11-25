package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDto {
    private Double price;
    private String photo;
    private String nameProcedure;
    private Long id;
}

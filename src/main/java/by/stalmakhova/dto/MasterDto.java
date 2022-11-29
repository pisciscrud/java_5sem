package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDto {
    private Long id;
    private String nameMaster;
    private String nameProcedure;
    private double priceProcedure;
}

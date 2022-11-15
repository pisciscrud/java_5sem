package by.stalmakhova.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFromServer {
    // private Long Id;
    private BigDecimal price;
    private String nameProcedure;

}

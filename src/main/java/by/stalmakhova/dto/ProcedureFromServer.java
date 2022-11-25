package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFromServer {
    // private Long Id;
    private double price;
    private String nameProcedure;
    private String Photo;

}

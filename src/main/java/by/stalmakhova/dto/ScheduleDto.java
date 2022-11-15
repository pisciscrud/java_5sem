package by.stalmakhova.dto;

import by.stalmakhova.entity.Pet;
import by.stalmakhova.entity.Status;
import by.stalmakhova.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long id;
    private String date;
    private String time;
    private Pet name_pet;
    private String name_procedure;
    private User full_name;
    private String price;
    private Status status;
}

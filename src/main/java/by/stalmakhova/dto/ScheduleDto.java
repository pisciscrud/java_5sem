package by.stalmakhova.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private String date;
    private String time;
    private String statusName;
    private String masterName;
    private String procedureName;
    private String petNickname;
    private String ownerLogin;

}

package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteHistory {
    private String date;
    private String time;
    private String statusName;
    private String masterName;
    private String procedureName;
    private String petNickname;
}

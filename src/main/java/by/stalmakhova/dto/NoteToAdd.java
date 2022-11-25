package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteToAdd {
    private String nickname;
    private String date;
    private String time;
    private String masterName;
    private String procedureName;
}

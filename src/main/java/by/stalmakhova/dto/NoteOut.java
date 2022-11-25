package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteOut {
    private Long id;
    private String nickname;
    private String login ;
    private String date;
    private String time;
    private String statusName;
    private String masterName;
    private String procedureName;
}

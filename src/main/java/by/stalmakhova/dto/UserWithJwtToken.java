package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithJwtToken {

    private String login;

   // private Boolean isAdmin;

    private Long role_id;
    private String token;
}

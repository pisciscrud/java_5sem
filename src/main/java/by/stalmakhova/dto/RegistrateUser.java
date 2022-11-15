package by.stalmakhova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrateUser {

    private String fullName;
    private String login;
    private String password;
    private String email;
}

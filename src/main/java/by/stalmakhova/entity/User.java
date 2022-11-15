package by.stalmakhova.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "full_name", nullable = false, length = 30)
    private String fullName;

    @Size(max = 30)
    @NotNull
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Size(max = 64)
    @NotNull
    @Column(name = "login", nullable = false, length = 64)
    private String login;

    @Size(max = 30)
    @NotNull
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @NotNull
    @Column(name = "id_role", nullable = false)
    private long idRole = 2L;


}
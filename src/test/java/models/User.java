package models;

import lombok.*;

@Data
@ToString
@Builder
public class User {
    String firstname;
    String surname;
    String email;
    boolean isActive;
    int age;
    int id;
    String username;
    String password;
}

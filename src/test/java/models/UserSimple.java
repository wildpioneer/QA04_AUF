package models;

import lombok.*;

@Data
@ToString(exclude = "id")
@Builder
public class UserSimple {
    String firstname;
    String surname;
    String email;
    boolean isActive;
    int age;
    int id;
    String username;
    String password;
}

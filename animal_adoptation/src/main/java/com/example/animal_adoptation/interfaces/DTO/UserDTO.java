package com.example.animal_adoptation.interfaces.DTO;

import com.example.animal_adoptation.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    // Constructor que convierte de User a UserDTO
    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
        }
    }

    // Método para convertir de UserDTO a User
    public User toModel() {
        return new User(id, username, password);
    }
}

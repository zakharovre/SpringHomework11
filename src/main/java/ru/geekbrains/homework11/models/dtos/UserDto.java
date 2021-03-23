package ru.geekbrains.homework11.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.homework11.models.entities.User;

@Data
@NoArgsConstructor
public class UserDto {
    private String userName;
    private int score;

    public UserDto(User user) {
        this.userName = user.getUsername();
        this.score = user.getScore();
    }
}

package ru.streetfootball.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.streetfootball.util.enums.Position;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDTO {

    private Long id;

    private Long telegramId;

    private String nickname;

    private boolean male;

    private LocalDate birthDate;

    private Position primaryPosition;

    private Position secondaryPosition;

    private String avatar;

    private Instant createdAt;

}
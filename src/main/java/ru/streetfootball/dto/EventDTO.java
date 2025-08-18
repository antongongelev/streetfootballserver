package ru.streetfootball.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {

    private Long id;

    private StadiumDTO stadium;

    private Instant beginAt;

    private boolean isFinished;

    private boolean isCancelled;

    private Integer minPlayers;

    private Instant createdAt;

}
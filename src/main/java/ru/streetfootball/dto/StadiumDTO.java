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
public class StadiumDTO {

    private Long id;

    private String address;

    private String description;

    private String capacity;

    private LocationDTO location;

    private String avatar;

    private Instant createdAt;


}
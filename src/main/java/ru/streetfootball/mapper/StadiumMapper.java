package ru.streetfootball.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.streetfootball.dto.StadiumDTO;
import ru.streetfootball.entity.Stadium;

@Component
@RequiredArgsConstructor
public class StadiumMapper implements Mapper<Stadium, StadiumDTO> {

    private final LocationMapper locationMapper;

    @Override
    public StadiumDTO dtoFromEntity(Stadium stadium) {

        var location = locationMapper.dtoFromEntity(stadium.getLocation());

        return StadiumDTO.builder()
                .id(stadium.getId())
                .address(stadium.getAddress())
                .description(stadium.getDescription())
                .capacity(stadium.getCapacity())
                .location(location)
                .avatar(stadium.getAvatar())
                .createdAt(stadium.getCreatedAt())
                .build();
    }

    @Override
    public Stadium entityFromDto(StadiumDTO stadium) {

        var location = locationMapper.entityFromDto(stadium.getLocation());

        return Stadium.builder()
                .id(stadium.getId())
                .address(stadium.getAddress())
                .description(stadium.getDescription())
                .capacity(stadium.getCapacity())
                .location(location)
                .avatar(stadium.getAvatar())
                .createdAt(stadium.getCreatedAt())
                .build();
    }

    @Override
    public void updateEntity(Stadium stadium, StadiumDTO stadiumDTO) {

    }

}

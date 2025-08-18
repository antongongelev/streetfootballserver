package ru.streetfootball.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.streetfootball.dto.EventDTO;
import ru.streetfootball.entity.Event;

@Component
@RequiredArgsConstructor
public class EventMapper implements Mapper<Event, EventDTO> {

    private final StadiumMapper stadiumMapper;

    @Override
    public EventDTO dtoFromEntity(Event event) {

        var stadium = stadiumMapper.dtoFromEntity(event.getStadium());

        return EventDTO.builder()
                .id(event.getId())
                .stadium(stadium)
                .beginAt(event.getBeginAt())
                .isFinished(event.isFinished())
                .isCancelled(event.isCancelled())
                .minPlayers(event.getMinPlayers())
                .createdAt(event.getCreatedAt())
                .build();
    }

    @Override
    public Event entityFromDto(EventDTO event) {

        var stadium = stadiumMapper.entityFromDto(event.getStadium());

        return Event.builder()
                .id(event.getId())
                .stadium(stadium)
                .beginAt(event.getBeginAt())
                .isFinished(event.isFinished())
                .isCancelled(event.isCancelled())
                .minPlayers(event.getMinPlayers())
                .createdAt(event.getCreatedAt())
                .build();
    }
}


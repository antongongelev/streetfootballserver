package ru.streetfootball.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.streetfootball.dto.EventDTO;
import ru.streetfootball.mapper.EventMapper;
import ru.streetfootball.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventDTO> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::dtoFromEntity)
                .toList();
    }

    public EventDTO findById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::dtoFromEntity)
                .orElse(null);
    }

    public EventDTO save(EventDTO event) {
        var saved = eventRepository.save(eventMapper.entityFromDto(event));
        return eventMapper.dtoFromEntity(saved);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
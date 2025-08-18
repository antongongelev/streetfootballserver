package ru.streetfootball.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.streetfootball.dto.StadiumDTO;
import ru.streetfootball.mapper.StadiumMapper;
import ru.streetfootball.repository.StadiumRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    public List<StadiumDTO> findAll() {
        return stadiumRepository.findAll()
                .stream()
                .map(stadiumMapper::dtoFromEntity)
                .toList();
    }

    public StadiumDTO findById(Long id) {
        return stadiumRepository.findById(id)
                .map(stadiumMapper::dtoFromEntity)
                .orElse(null);
    }

    public StadiumDTO save(StadiumDTO stadium) {
        var saved = stadiumRepository.save(stadiumMapper.entityFromDto(stadium));
        return stadiumMapper.dtoFromEntity(saved);
    }

    public void deleteById(Long id) {
        stadiumRepository.deleteById(id);
    }
}
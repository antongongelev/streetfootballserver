package ru.streetfootball.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.streetfootball.dto.PlayerDTO;
import ru.streetfootball.mapper.PlayerMapper;
import ru.streetfootball.repository.PlayerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::dtoFromEntity)
                .toList();
    }

    public PlayerDTO findById(Long id) {
        return playerRepository.findById(id)
                .map(playerMapper::dtoFromEntity)
                .orElse(null);
    }

    public PlayerDTO findByTelegramId(Long telegramId) {
        return playerRepository.findByTelegramId(telegramId)
                .map(playerMapper::dtoFromEntity)
                .orElse(null);
    }

    public PlayerDTO save(PlayerDTO player) {
        var saved = playerRepository.save(playerMapper.entityFromDto(player));
        return playerMapper.dtoFromEntity(saved);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
package ru.streetfootball.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.streetfootball.dto.PlayerDTO;
import ru.streetfootball.mapper.PlayerMapper;
import ru.streetfootball.repository.PlayerRepository;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final AvatarService avatarService;

    public PlayerDTO findByTelegramId(Long telegramId) {
        return playerRepository.findByTelegramId(telegramId)
                .map(playerMapper::dtoFromEntity)
                .orElse(null);
    }

    public PlayerDTO create(PlayerDTO player) {
        var saved = playerRepository.save(playerMapper.entityFromDto(player));
        return playerMapper.dtoFromEntity(saved);
    }

    public PlayerDTO update(PlayerDTO player) {
        var saved = playerRepository.save(playerMapper.entityFromDto(player));
        return playerMapper.dtoFromEntity(saved);
    }

    public void uploadAvatar(Long telegramId, MultipartFile avatarFile) {
        avatarService.uploadAvatar(telegramId, avatarFile);
    }

    public Resource getAvatar(Long telegramId) {
        return avatarService.getAvatar(telegramId);
    }

}
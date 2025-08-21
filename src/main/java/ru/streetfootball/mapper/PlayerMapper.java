package ru.streetfootball.mapper;

import org.springframework.stereotype.Component;
import ru.streetfootball.dto.PlayerDTO;
import ru.streetfootball.entity.Player;

@Component
public class PlayerMapper implements Mapper<Player, PlayerDTO> {

    @Override
    public PlayerDTO dtoFromEntity(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .telegramId(player.getTelegramId())
                .nickname(player.getNickname())
                .male(player.isMale())
                .birthDate(player.getBirthDate())
                .primaryPosition(player.getPrimaryPosition())
                .secondaryPosition(player.getSecondaryPosition())
                .avatar(player.getAvatar())
                .createdAt(player.getCreatedAt())
                .build();
    }

    @Override
    public Player entityFromDto(PlayerDTO player) {
        return Player.builder()
                .id(player.getId())
                .telegramId(player.getTelegramId())
                .nickname(player.getNickname())
                .male(player.isMale())
                .birthDate(player.getBirthDate())
                .primaryPosition(player.getPrimaryPosition())
                .secondaryPosition(player.getSecondaryPosition())
                .avatar(player.getAvatar())
                .createdAt(player.getCreatedAt())
                .build();
    }
}

package ru.streetfootball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.streetfootball.entity.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByTelegramId(Long telegramId);

}
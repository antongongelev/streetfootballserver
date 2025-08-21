package ru.streetfootball.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.streetfootball.dto.PlayerDTO;
import ru.streetfootball.service.PlayerService;

import java.util.Objects;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{telegramId}")
    public ResponseEntity<PlayerDTO> findByTelegramId(@PathVariable Long telegramId) {
        var player = playerService.findByTelegramId(telegramId);
        return Objects.isNull(player)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(player);
    }

    @PostMapping("/update")
    public ResponseEntity<PlayerDTO> update(@RequestBody PlayerDTO player) {
        return ResponseEntity.ok(playerService.update(player));
    }

    @PostMapping("/create")
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDTO player) {
        return ResponseEntity.ok(playerService.create(player));
    }

}

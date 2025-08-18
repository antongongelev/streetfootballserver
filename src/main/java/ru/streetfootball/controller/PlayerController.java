package ru.streetfootball.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.streetfootball.dto.PlayerDTO;
import ru.streetfootball.service.PlayerService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    @GetMapping("/telegram/{telegramId}")
    public ResponseEntity<PlayerDTO> findByTelegramId(@PathVariable Long telegramId) {
        var player = playerService.findByTelegramId(telegramId);
        return Objects.isNull(player)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO player) {
        return ResponseEntity.ok(playerService.save(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

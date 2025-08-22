package ru.streetfootball.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping("/avatar/{telegramId}")
    public ResponseEntity<Resource> getAvatar(@PathVariable Long telegramId) {
        var avatarResource = playerService.getAvatar(telegramId);

        if (avatarResource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(avatarResource);
    }

    @PostMapping(value = "/avatar/{telegramId}", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadAvatar(@PathVariable Long telegramId, @RequestParam("avatar") MultipartFile avatarFile) {
        playerService.uploadAvatar(telegramId, avatarFile);
        return ResponseEntity.noContent().build();
    }

}


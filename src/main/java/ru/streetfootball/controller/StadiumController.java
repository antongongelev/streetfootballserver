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
import ru.streetfootball.dto.StadiumDTO;
import ru.streetfootball.service.StadiumService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    public ResponseEntity<List<StadiumDTO>> findAll() {
        return ResponseEntity.ok(stadiumService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadiumDTO> findById(@PathVariable Long id) {
        var stadium = stadiumService.findById(id);
        return Objects.isNull(stadium)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(stadium);
    }

    @PostMapping
    public ResponseEntity<StadiumDTO> save(@RequestBody StadiumDTO stadium) {
        return ResponseEntity.ok(stadiumService.save(stadium));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        stadiumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

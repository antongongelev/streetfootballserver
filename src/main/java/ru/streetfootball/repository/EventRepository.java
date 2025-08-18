package ru.streetfootball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.streetfootball.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
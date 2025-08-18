package ru.streetfootball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.streetfootball.entity.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
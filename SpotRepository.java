package com.parking.builder.repo;
import com.parking.builder.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface SpotRepository extends JpaRepository<Spot,Long>{ Optional<Spot> findBySpotNumber(String spotNumber); List<Spot> findByOccupiedFalseAndType(SpotType type); List<Spot> findByOccupiedFalse(); }

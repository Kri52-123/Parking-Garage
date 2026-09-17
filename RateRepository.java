package com.parking.builder.repo;
import com.parking.builder.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RateRepository extends JpaRepository<Rate,Long>{ Optional<Rate> findBySpotType(SpotType type); }

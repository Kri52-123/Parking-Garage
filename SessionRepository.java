package com.parking.builder.repo;
import com.parking.builder.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface SessionRepository extends JpaRepository<ParkingSession,Long>{ Optional<ParkingSession> findByIdAndStatus(Long id,SessionStatus status); Optional<ParkingSession> findByVehicleNumberAndStatus(String vehicle,SessionStatus status); List<ParkingSession> findByStatus(SessionStatus status); }

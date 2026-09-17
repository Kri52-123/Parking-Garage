package com.parking.builder.service;

import com.parking.builder.model.*;
import com.parking.builder.repo.*;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {
    private final SpotRepository spotRepo; private final RateRepository rateRepo; private final SessionRepository sessionRepo;
    public ParkingService(SpotRepository s,RateRepository r,SessionRepository p){spotRepo=s;rateRepo=r;sessionRepo=p;}

    public List<Spot> availableSpots(SpotType type){ return type==null?spotRepo.findByOccupiedFalse():spotRepo.findByOccupiedFalseAndType(type); }

    public ParkingSession checkIn(String vehicleNumber, SpotType type){
        if(vehicleNumber==null||vehicleNumber.isBlank()) throw new IllegalArgumentException("Vehicle number is required");
        if(sessionRepo.findByVehicleNumberAndStatus(vehicleNumber.trim().toUpperCase(),SessionStatus.ACTIVE).isPresent()) throw new IllegalArgumentException("Vehicle is already parked");
        Spot spot=availableSpots(type).stream().findFirst().orElseThrow(()->new IllegalArgumentException("No available spot for type: "+type));
        spot.setOccupied(true); spotRepo.save(spot);
        return sessionRepo.save(new ParkingSession(vehicleNumber.trim().toUpperCase(),spot.getSpotNumber(),LocalDateTime.now()));
    }

    public ParkingSession checkOut(Long sessionId){
        ParkingSession session=sessionRepo.findByIdAndStatus(sessionId,SessionStatus.ACTIVE).orElseThrow(()->new IllegalArgumentException("Active parking session not found"));
        Spot spot=spotRepo.findBySpotNumber(session.getSpotNumber()).orElseThrow(()->new IllegalArgumentException("Parking spot not found"));
        LocalDateTime exit=LocalDateTime.now();
        long minutes=Math.max(1,Duration.between(session.getEntryTime(),exit).toMinutes());
        long hours=(minutes+59)/60;
        Rate rate=rateRepo.findBySpotType(spot.getType()).orElseThrow(()->new IllegalArgumentException("Rate not configured"));
        double fee=rate.getFirstHour();
        if(hours>1) fee += (hours-1)*rate.getAdditionalHour();
        fee=Math.min(fee,rate.getDailyCap());
        session.setExitTime(exit); session.setFee(fee); session.setStatus(SessionStatus.COMPLETED);
        spot.setOccupied(false); spotRepo.save(spot);
        return sessionRepo.save(session);
    }
    public List<ParkingSession> activeSessions(){return sessionRepo.findByStatus(SessionStatus.ACTIVE);}
    public List<ParkingSession> allSessions(){return sessionRepo.findAll();}
}

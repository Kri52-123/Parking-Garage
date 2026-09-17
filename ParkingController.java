package com.parking.builder.controller;

import com.parking.builder.model.*;
import com.parking.builder.service.ParkingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService service;
    public ParkingController(ParkingService service){this.service=service;}
    @GetMapping("/spots") public List<Spot> spots(@RequestParam(required=false) SpotType type){return service.availableSpots(type);}
    @PostMapping("/check-in") public ParkingSession checkIn(@RequestBody CheckInRequest r){return service.checkIn(r.vehicleNumber(),r.type());}
    @PostMapping("/check-out/{id}") public ParkingSession checkOut(@PathVariable Long id){return service.checkOut(id);}
    @GetMapping("/active") public List<ParkingSession> active(){return service.activeSessions();}
    @GetMapping("/sessions") public List<ParkingSession> sessions(){return service.allSessions();}
    public record CheckInRequest(String vehicleNumber,SpotType type){}
}

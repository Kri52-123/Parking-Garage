package com.parking.builder.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="parking_sessions")
public class ParkingSession {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String vehicleNumber;
    @Column(nullable=false) private String spotNumber;
    @Column(nullable=false) private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double fee;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private SessionStatus status;
    public ParkingSession() {}
    public ParkingSession(String vehicleNumber,String spotNumber,LocalDateTime entryTime){this.vehicleNumber=vehicleNumber;this.spotNumber=spotNumber;this.entryTime=entryTime;this.status=SessionStatus.ACTIVE;}
    public Long getId(){return id;} public String getVehicleNumber(){return vehicleNumber;} public String getSpotNumber(){return spotNumber;} public LocalDateTime getEntryTime(){return entryTime;} public LocalDateTime getExitTime(){return exitTime;} public Double getFee(){return fee;} public SessionStatus getStatus(){return status;}
    public void setId(Long v){id=v;} public void setVehicleNumber(String v){vehicleNumber=v;} public void setSpotNumber(String v){spotNumber=v;} public void setEntryTime(LocalDateTime v){entryTime=v;} public void setExitTime(LocalDateTime v){exitTime=v;} public void setFee(Double v){fee=v;} public void setStatus(SessionStatus v){status=v;}
}

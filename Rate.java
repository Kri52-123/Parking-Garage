package com.parking.builder.model;

import jakarta.persistence.*;

@Entity
@Table(name="rates", uniqueConstraints=@UniqueConstraint(columnNames="spotType"))
public class Rate {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private SpotType spotType;
    @Column(nullable=false) private double firstHour;
    @Column(nullable=false) private double additionalHour;
    @Column(nullable=false) private double dailyCap;
    public Rate() {}
    public Rate(SpotType type,double firstHour,double additionalHour,double dailyCap){this.spotType=type;this.firstHour=firstHour;this.additionalHour=additionalHour;this.dailyCap=dailyCap;}
    public Long getId(){return id;} public SpotType getSpotType(){return spotType;} public double getFirstHour(){return firstHour;} public double getAdditionalHour(){return additionalHour;} public double getDailyCap(){return dailyCap;}
    public void setId(Long v){id=v;} public void setSpotType(SpotType v){spotType=v;} public void setFirstHour(double v){firstHour=v;} public void setAdditionalHour(double v){additionalHour=v;} public void setDailyCap(double v){dailyCap=v;}
}

package com.parking.builder.model;

import jakarta.persistence.*;

@Entity
@Table(name="spots", uniqueConstraints=@UniqueConstraint(columnNames="spotNumber"))
public class Spot {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String spotNumber;
    @Column(nullable=false) private int level;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private SpotType type;
    @Column(nullable=false) private boolean occupied=false;
    public Spot() {}
    public Spot(String spotNumber,int level,SpotType type){this.spotNumber=spotNumber;this.level=level;this.type=type;}
    public Long getId(){return id;} public String getSpotNumber(){return spotNumber;} public int getLevel(){return level;} public SpotType getType(){return type;} public boolean isOccupied(){return occupied;}
    public void setId(Long id){this.id=id;} public void setSpotNumber(String v){spotNumber=v;} public void setLevel(int v){level=v;} public void setType(SpotType v){type=v;} public void setOccupied(boolean v){occupied=v;}
}

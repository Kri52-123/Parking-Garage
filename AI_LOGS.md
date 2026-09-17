# AI LOGS

This file documents AI-assisted implementation notes for transparency.

- Generated a clean Spring Boot package structure under `com.parking.builder`.
- Used JPA entities for User, Spot, Rate and ParkingSession.
- Implemented the business rule that part-hours round up.
- Implemented first-hour, additional-hour and daily-cap pricing.
- Added a global exception handler returning JSON errors.
- Added seed data so the application can run immediately without manual database setup.
- Added a minimal static browser UI for check-in, check-out and spot viewing.

The code should be reviewed and tested by the candidate before submission.
Yes. I can continue without the data-analysis tool.

I’ll provide the complete project file-by-file for the parking-garage Builder Round: Java Spring Boot backend + H2 persistent database + REST APIs + HTML/CSS/JS frontend + login/register + search + pagination + sorting + README + REASONING.

Because the project is fairly large, I’ll structure it so you can copy the files directly into GitHub Codespaces.

Project structure
parking-garage/
│
├── pom.xml
│
├── README.md
├── REASONING.md
├── AI_LOGS.md
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── parking/
        │           ├── ParkingApplication.java
        │           │
        │           ├── config/
        │           │   ├── DataInitializer.java
        │           │   └── GlobalExceptionHandler.java
        │           │
        │           ├── controller/
        │           │   ├── AuthController.java
        │           │   ├── GarageController.java
        │           │   └── ParkingController.java
        │           │
        │           ├── model/
        │           │   ├── User.java
        │           │   ├── Garage.java
        │           │   ├── Vehicle.java
        │           │   ├── ParkingSpot.java
        │           │   ├── ParkingTicket.java
        │           │   ├── SpotType.java
        │           │   ├── VehicleType.java
        │           │   └── TicketStatus.java
        │           │
        │           ├── repository/
        │           │   ├── UserRepository.java
        │           │   ├── GarageRepository.java
        │           │   ├── VehicleRepository.java
        │           │   ├── ParkingSpotRepository.java
        │           │   └── ParkingTicketRepository.java
        │           │
        │           └── service/
        │               ├── AuthService.java
        │               └── ParkingService.java
        │
        └── resources/
            ├── application.properties
            │
            └── static/
                ├── index.html
                ├── style.css
                └── app.js
Important implementation choice

For the 2.5-hour assessment, use:

Frontend
HTML + CSS + JavaScript

        ↓ REST API

Backend
Spring Boot + Java

        ↓ JPA

Database
H2 file database

This is much faster to implement in Codespaces than setting up React + Node + PostgreSQL separately.

The H2 database is persistent, so it satisfies the requirement for real database persistence rather than using only Java HashMaps.

Run command

Once the files are in Codespaces:

mvn spring-boot:run

Then open:

http://localhost:8080
Core APIs
POST /api/auth/register
POST /api/auth/login

GET  /api/garages
POST /api/garages
GET  /api/garages/{id}

POST /api/garages/spots

POST /api/parking/check-in
POST /api/parking/check-out

GET /api/parking/availability/{garageId}
GET /api/parking/spots/{garageId}

GET /api/parking/search?plate=RJ14
GET /api/parking/active?plate=RJ14AB1234

GET /api/parking/history

The backend will enforce the important rules:

EV → EV spot only
CAR → Compact → Standard
Occupied spot → cannot be allocated
Duplicate active plate → rejected

Billing:
first hour = firstHourRate
additional hour = extraHourRate
partial hour = rounded up
maximum = dailyCap

One important point for the actual assessment: AI_LOGS.md must contain the complete AI conversation from the assessment, exactly as required by their instructions. Don't use a manually summarized log as the actual submission.

write the code frontend and backend for this project 

Data analysis isn't available right now. Do you want to continue without it? 

this project structure complete code write 

Yes. Your screenshot shows that you currently have a single ParkingGarage.java console-based Java file plus README.md, REASONING.md, and AI_LOGS.md.

For the Round-2 Builder requirement, convert it into a full-stack application:

Backend: Java + Spring Boot + REST API
Database: H2 persistent database
Frontend: HTML + CSS + JavaScript
Login/Register
Check-in / Check-out
Automatic spot allocation
EV-only spots
Automatic fee calculation
Plate-number search
Pagination + sorting
README / REASONING / AI_LOGS

Below is the complete structure and code you can put into your GitHub Codespace.

1. Complete project structure

Create this:

ParkingGarage/
│
├── pom.xml
├── README.md
├── REASONING.md
├── AI_LOGS.md
├── .gitignore
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── parking/
        │           ├── ParkingApplication.java
        │           │
        │           ├── model/
        │           │   ├── User.java
        │           │   ├── Garage.java
        │           │   ├── ParkingSpot.java
        │           │   ├── Vehicle.java
        │           │   ├── ParkingTicket.java
        │           │   ├── SpotType.java
        │           │   ├── VehicleType.java
        │           │   └── TicketStatus.java
        │           │
        │           ├── repository/
        │           │   ├── UserRepository.java
        │           │   ├── GarageRepository.java
        │           │   ├── ParkingSpotRepository.java
        │           │   ├── VehicleRepository.java
        │           │   └── ParkingTicketRepository.java
        │           │
        │           ├── service/
        │           │   ├── AuthService.java
        │           │   └── ParkingService.java
        │           │
        │           ├── controller/
        │           │   ├── AuthController.java
        │           │   ├── GarageController.java
        │           │   └── ParkingController.java
        │           │
        │           └── config/
        │               ├── DataInitializer.java
        │               └── GlobalExceptionHandler.java
        │
        └── resources/
            ├── application.properties
            │
            └── static/
                ├── index.html
                ├── style.css
                └── app.js
2. pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.5</version>
        <relativePath/>
    </parent>

    <groupId>com.parking</groupId>
    <artifactId>parking-garage</artifactId>
    <version>1.0.0</version>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>

        <!-- REST API -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Password hashing -->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-crypto</artifactId>
        </dependency>

        <!-- H2 -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
3. application.properties
server.port=8080

spring.datasource.url=jdbc:h2:file:./data/parkingdb;AUTO_SERVER=TRUE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jackson.serialization.write-dates-as-timestamps=false

The important part is:

jdbc:h2:file:./data/parkingdb

This means the database is persistent, rather than being lost every time the application restarts.

4. Main application
ParkingApplication.java
package com.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {

        SpringApplication.run(ParkingApplication.class, args);

    }
}
5. Models
SpotType.java
package com.parking.model;

public enum SpotType {

    COMPACT,
    STANDARD,
    EV
}
VehicleType.java
package com.parking.model;

public enum VehicleType {

    CAR,
    EV
}
TicketStatus.java
package com.parking.model;

public enum TicketStatus {

    ACTIVE,
    COMPLETED
}
6. User
User.java
package com.parking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {
    }

    public User(String name, String email, String passwordHash) {

        this.name = name;
        this.email = email.toLowerCase().trim();
        this.passwordHash = passwordHash;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
7. Garage
Garage.java
package com.parking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "garages")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double firstHourRate;

    @Column(nullable = false)
    private double extraHourRate;

    @Column(nullable = false)
    private double dailyCap;

    public Garage() {
    }

    public Garage(
            String name,
            double firstHourRate,
            double extraHourRate,
            double dailyCap
    ) {

        this.name = name;
        this.firstHourRate = firstHourRate;
        this.extraHourRate = extraHourRate;
        this.dailyCap = dailyCap;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getFirstHourRate() {
        return firstHourRate;
    }

    public double getExtraHourRate() {
        return extraHourRate;
    }

    public double getDailyCap() {
        return dailyCap;
    }
}
8. Parking Spot
ParkingSpot.java
package com.parking.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "parking_spots",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"garage_id", "spot_number"}
        )
)
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "garage_id")
    private Garage garage;

    private int floor;

    @Column(name = "spot_number", nullable = false)
    private String spotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotType spotType;

    private boolean occupied = false;

    public ParkingSpot() {
    }

    public ParkingSpot(
            Garage garage,
            int floor,
            String spotNumber,
            SpotType spotType
    ) {

        this.garage = garage;
        this.floor = floor;
        this.spotNumber = spotNumber;
        this.spotType = spotType;

    }

    public Long getId() {
        return id;
    }

    public Garage getGarage() {
        return garage;
    }

    public int getFloor() {
        return floor;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
9. Vehicle
Vehicle.java
package com.parking.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "vehicles",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "plateNumber"
        )
)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String plateNumber, VehicleType vehicleType) {

        this.plateNumber =
                plateNumber.trim().toUpperCase();

        this.vehicleType = vehicleType;
    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
10. Parking Ticket
ParkingTicket.java
package com.parking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "parking_tickets",
        indexes = {
                @Index(
                        name = "idx_ticket_plate",
                        columnList = "plateNumber"
                ),
                @Index(
                        name = "idx_ticket_status",
                        columnList = "status"
                )
        }
)
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String plateNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "spot_id")
    private ParkingSpot spot;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Double fee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.ACTIVE;

    public ParkingTicket() {
    }

    public ParkingTicket(
            String plateNumber,
            VehicleType vehicleType,
            ParkingSpot spot
    ) {

        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.spot = spot;
        this.checkIn = LocalDateTime.now();

    }

    public void checkout(
            LocalDateTime checkOut,
            double fee
    ) {

        this.checkOut = checkOut;
        this.fee = fee;
        this.status = TicketStatus.COMPLETED;

    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public Double getFee() {
        return fee;
    }

    public TicketStatus getStatus() {
        return status;
    }
}
11. Repositories
UserRepository.java
package com.parking.repository;

import com.parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
GarageRepository.java
package com.parking.repository;

import com.parking.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository
        extends JpaRepository<Garage, Long> {
}
ParkingSpotRepository.java
package com.parking.repository;

import com.parking.model.ParkingSpot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository
        extends JpaRepository<ParkingSpot, Long> {

    List<ParkingSpot> findByGarageId(Long garageId);

    List<ParkingSpot> findByGarageIdAndOccupiedFalse(
            Long garageId
    );
}
VehicleRepository.java
package com.parking.repository;

import com.parking.model.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlateNumber(
            String plateNumber
    );
}
ParkingTicketRepository.java
package com.parking.repository;

import com.parking.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository
        extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket>
    findByPlateNumberAndStatus(
            String plateNumber,
            TicketStatus status
    );

    Page<ParkingTicket> findAll(Pageable pageable);

    List<ParkingTicket>
    findTop20ByPlateNumberContainingIgnoreCaseOrderByCheckInDesc(
            String plateNumber
    );
}
12. Authentication Service
AuthService.java
package com.parking.service;

import com.parking.model.User;
import com.parking.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(
            String name,
            String email,
            String password
    ) {

        email = email.trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {

            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }

        if (password.length() < 6) {

            throw new IllegalArgumentException(
                    "Password must contain at least 6 characters"
            );
        }

        String hashedPassword =
                encoder.encode(password);

        User user =
                new User(
                        name,
                        email,
                        hashedPassword
                );

        return userRepository.save(user);
    }

    public User login(
            String email,
            String password
    ) {

        User user =
                userRepository
                        .findByEmail(
                                email.trim().toLowerCase()
                        )
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Invalid email or password"
                                )
                        );

        if (!encoder.matches(
                password,
                user.getPasswordHash()
        )) {

            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        return user;
    }
}
13. Main Parking Logic

This is the most important backend file.

ParkingService.java
package com.parking.service;

import com.parking.model.*;
import com.parking.repository.*;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final GarageRepository garageRepository;
    private final ParkingSpotRepository spotRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingTicketRepository ticketRepository;

    public ParkingService(
            GarageRepository garageRepository,
            ParkingSpotRepository spotRepository,
            VehicleRepository vehicleRepository,
            ParkingTicketRepository ticketRepository
    ) {

        this.garageRepository = garageRepository;
        this.spotRepository = spotRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    // ================= CHECK IN =================

    @Transactional
    public ParkingTicket checkIn(
            Long garageId,
            String plate,
            VehicleType vehicleType
    ) {

        Garage garage =
                garageRepository.findById(garageId)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Garage not found"
                                )
                        );

        String normalizedPlate =
                plate.trim().toUpperCase();

        // Prevent duplicate active parking
        if (ticketRepository
                .findByPlateNumberAndStatus(
                        normalizedPlate,
                        TicketStatus.ACTIVE
                )
                .isPresent()) {

            throw new IllegalStateException(
                    "Vehicle is already parked"
            );
        }

        List<ParkingSpot> freeSpots =
                spotRepository
                        .findByGarageIdAndOccupiedFalse(
                                garageId
                        );

        ParkingSpot selectedSpot =
                chooseSpot(
                        freeSpots,
                        vehicleType
                );

        if (selectedSpot == null) {

            if (vehicleType == VehicleType.EV) {

                throw new IllegalStateException(
                        "No EV charging spot available"
                );

            }

            throw new IllegalStateException(
                    "No suitable parking spot available"
            );
        }

        Vehicle vehicle =
                vehicleRepository
                        .findByPlateNumber(
                                normalizedPlate
                        )
                        .orElseGet(
                                () -> vehicleRepository.save(
                                        new Vehicle(
                                                normalizedPlate,
                                                vehicleType
                                        )
                                )
                        );

        selectedSpot.setOccupied(true);

        spotRepository.save(selectedSpot);

        ParkingTicket ticket =
                new ParkingTicket(
                        vehicle.getPlateNumber(),
                        vehicle.getVehicleType(),
                        selectedSpot
                );

        return ticketRepository.save(ticket);
    }

    // ================= SPOT ALLOCATION =================

    private ParkingSpot chooseSpot(
            List<ParkingSpot> freeSpots,
            VehicleType vehicleType
    ) {

        Comparator<ParkingSpot> comparator =
                Comparator
                        .comparingInt(
                                ParkingSpot::getFloor
                        )
                        .thenComparing(
                                ParkingSpot::getSpotNumber
                        );

        // EV can ONLY use EV spot
        if (vehicleType == VehicleType.EV) {

            return freeSpots
                    .stream()
                    .filter(
                            s -> s.getSpotType()
                                    == SpotType.EV
                    )
                    .sorted(comparator)
                    .findFirst()
                    .orElse(null);
        }

        // Normal car → Compact first
        Optional<ParkingSpot> compact =
                freeSpots
                        .stream()
                        .filter(
                                s -> s.getSpotType()
                                        == SpotType.COMPACT
                        )
                        .sorted(comparator)
                        .findFirst();

        if (compact.isPresent()) {
            return compact.get();
        }

        // Then Standard
        return freeSpots
                .stream()
                .filter(
                        s -> s.getSpotType()
                                == SpotType.STANDARD
                )
                .sorted(comparator)
                .findFirst()
                .orElse(null);
    }

    // ================= CHECK OUT =================

    @Transactional
    public ParkingTicket checkOut(
            String plate
    ) {

        String normalizedPlate =
                plate.trim().toUpperCase();

        ParkingTicket ticket =
                ticketRepository
                        .findByPlateNumberAndStatus(
                                normalizedPlate,
                                TicketStatus.ACTIVE
                        )
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Active ticket not found"
                                )
                        );

        LocalDateTime now =
                LocalDateTime.now();

        double fee =
                calculateFee(
                        ticket.getCheckIn(),
                        now,
                        ticket.getSpot().getGarage()
                );

        // Free the parking spot
        ParkingSpot spot =
                ticket.getSpot();

        spot.setOccupied(false);

        spotRepository.save(spot);

        // Complete ticket
        ticket.checkout(
                now,
                fee
        );

        return ticketRepository.save(ticket);
    }

    // ================= BILL CALCULATION =================

    private double calculateFee(
            LocalDateTime start,
            LocalDateTime end,
            Garage garage
    ) {

        long minutes =
                Math.max(
                        1,
                        Duration
                                .between(start, end)
                                .toMinutes()
                );

        // Part hours rounded UP
        long hours =
                (minutes + 59) / 60;

        double fee =
                garage.getFirstHourRate();

        if (hours > 1) {

            fee +=
                    (hours - 1)
                            * garage.getExtraHourRate();
        }

        // Daily cap
        fee =
                Math.min(
                        fee,
                        garage.getDailyCap()
                );

        return fee;
    }

    // ================= AVAILABILITY =================

    public Map<String, Long> availability(
            Long garageId
    ) {

        List<ParkingSpot> freeSpots =
                spotRepository
                        .findByGarageIdAndOccupiedFalse(
                                garageId
                        );

        Map<String, Long> result =
                new LinkedHashMap<>();

        for (SpotType type : SpotType.values()) {

            long count =
                    freeSpots
                            .stream()
                            .filter(
                                    s -> s.getSpotType()
                                            == type
                            )
                            .count();

            result.put(
                    type.name(),
                    count
            );
        }

        return result;
    }

    // ================= GET SPOTS =================

    public List<ParkingSpot> getSpots(
            Long garageId
    ) {

        return spotRepository
                .findByGarageId(garageId);
    }

    // ================= SEARCH =================

    public List<ParkingTicket> search(
            String plate
    ) {

        return ticketRepository
                .findTop20ByPlateNumberContainingIgnoreCaseOrderByCheckInDesc(
                        plate
                );
    }

    // ================= ACTIVE TICKET =================

    public Optional<ParkingTicket> active(
            String plate
    ) {

        return ticketRepository
                .findByPlateNumberAndStatus(
                        plate.trim().toUpperCase(),
                        TicketStatus.ACTIVE
                );
    }

    // ================= PAGINATION + SORT =================

    public Page<ParkingTicket> history(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Set<String> allowedFields =
                Set.of(
                        "checkIn",
                        "checkOut",
                        "fee",
                        "plateNumber",
                        "status"
                );

        if (!allowedFields.contains(sortBy)) {
            sortBy = "checkIn";
        }

        page = Math.max(page, 0);

        size =
                Math.max(
                        1,
                        Math.min(size, 100)
                );

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("asc")
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;

        Sort sort =
                Sort.by(
                        sortDirection,
                        sortBy
                );

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        return ticketRepository.findAll(
                pageable
        );
    }
}
14. Authentication Controller
AuthController.java
package com.parking.controller;

import com.parking.model.User;
import com.parking.service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    public record RegisterRequest(

            @NotBlank
            String name,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String password

    ) {}

    public record LoginRequest(

            @NotBlank
            @Email
            String email,

            @NotBlank
            String password

    ) {}

    @PostMapping("/register")
    public Object register(
            @Valid @RequestBody RegisterRequest request
    ) {

        User user =
                service.register(
                        request.name(),
                        request.email(),
                        request.password()
                );

        return Map.of(
                "message",
                "Registration successful",

                "userId",
                user.getId(),

                "name",
                user.getName(),

                "email",
                user.getEmail()
        );
    }

    @PostMapping("/login")
    public Object login(
            @Valid @RequestBody LoginRequest request
    ) {

        User user =
                service.login(
                        request.email(),
                        request.password()
                );

        return Map.of(
                "message",
                "Login successful",

                "userId",
                user.getId(),

                "name",
                user.getName(),

                "email",
                user.getEmail()
        );
    }
}
15. Parking Controller
ParkingController.java
package com.parking.controller;

import com.parking.model.*;
import com.parking.service.ParkingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    public record CheckInRequest(

            @NotNull
            Long garageId,

            @NotBlank
            String plateNumber,

            @NotNull
            VehicleType vehicleType

    ) {}

    public record CheckOutRequest(

            @NotBlank
            String plateNumber

    ) {}

    @PostMapping("/check-in")
    public ParkingTicket checkIn(
            @Valid @RequestBody CheckInRequest request
    ) {

        return service.checkIn(
                request.garageId(),
                request.plateNumber(),
                request.vehicleType()
        );
    }

    @PostMapping("/check-out")
    public ParkingTicket checkOut(
            @Valid @RequestBody CheckOutRequest request
    ) {

        return service.checkOut(
                request.plateNumber()
        );
    }

    @GetMapping("/availability/{garageId}")
    public Map<String, Long> availability(
            @PathVariable Long garageId
    ) {

        return service.availability(
                garageId
        );
    }

    @GetMapping("/spots/{garageId}")
    public List<ParkingSpot> spots(
            @PathVariable Long garageId
    ) {

        return service.getSpots(
                garageId
        );
    }

    @GetMapping("/search")
    public List<ParkingTicket> search(
            @RequestParam String plate
    ) {

        return service.search(plate);
    }

    @GetMapping("/active")
    public ParkingTicket active(
            @RequestParam String plate
    ) {

        return service
                .active(plate)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No active ticket found"
                        )
                );
    }

    @GetMapping("/history")
    public Page<ParkingTicket> history(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "checkIn")
            String sortBy,

            @RequestParam(defaultValue = "desc")
            String direction

    ) {

        return service.history(
                page,
                size,
                sortBy,
                direction
        );
    }
}
16. Garage Controller
GarageController.java
package com.parking.controller;

import com.parking.model.*;
import com.parking.repository.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    private final GarageRepository garageRepository;
    private final ParkingSpotRepository spotRepository;

    public GarageController(
            GarageRepository garageRepository,
            ParkingSpotRepository spotRepository
    ) {

        this.garageRepository = garageRepository;
        this.spotRepository = spotRepository;
    }

    public record GarageRequest(

            @NotBlank
            String name,

            @PositiveOrZero
            double firstHourRate,

            @PositiveOrZero
            double extraHourRate,

            @Positive
            double dailyCap

    ) {}

    public record SpotRequest(

            @NotNull
            Long garageId,

            @Min(1)
            int floor,

            @NotBlank
            String spotNumber,

            @NotNull
            SpotType spotType

    ) {}

    @GetMapping
    public List<Garage> all() {

        return garageRepository.findAll();
    }

    @PostMapping
    public Garage create(
            @Valid @RequestBody GarageRequest request
    ) {

        return garageRepository.save(
                new Garage(
                        request.name(),
                        request.firstHourRate(),
                        request.extraHourRate(),
                        request.dailyCap()
                )
        );
    }

    @PostMapping("/spots")
    public ParkingSpot createSpot(
            @Valid @RequestBody SpotRequest request
    ) {

        Garage garage =
                garageRepository
                        .findById(request.garageId())
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Garage not found"
                                )
                        );

        return spotRepository.save(
                new ParkingSpot(
                        garage,
                        request.floor(),
                        request.spotNumber(),
                        request.spotType()
                )
        );
    }

    @GetMapping("/{id}")
    public Garage get(
            @PathVariable Long id
    ) {

        return garageRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Garage not found"
                        )
                );
    }
}
17. Database Initializer
DataInitializer.java
package com.parking.config;

import com.parking.model.*;
import com.parking.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedDatabase(
            GarageRepository garageRepository,
            ParkingSpotRepository spotRepository
    ) {

        return args -> {

            if (garageRepository.count() == 0) {

                Garage garage =
                        garageRepository.save(
                                new Garage(
                                        "City Centre Parking",
                                        50,
                                        30,
                                        300
                                )
                        );

                int number = 1;

                for (int floor = 1; floor <= 2; floor++) {

                    // Compact
                    for (int i = 0; i < 5; i++) {

                        spotRepository.save(
                                new ParkingSpot(
                                        garage,
                                        floor,
                                        "C-" + number++,
                                        SpotType.COMPACT
                                )
                        );
                    }

                    // Standard
                    for (int i = 0; i < 4; i++) {

                        spotRepository.save(
                                new ParkingSpot(
                                        garage,
                                        floor,
                                        "S-" + number++,
                                        SpotType.STANDARD
                                )
                        );
                    }

                    // EV
                    for (int i = 0; i < 2; i++) {

                        spotRepository.save(
                                new ParkingSpot(
                                        garage,
                                        floor,
                                        "EV-" + number++,
                                        SpotType.EV
                                )
                        );
                    }
                }
            }
        };
    }
}

This automatically creates:

Floor 1
C-1 ... C-5
S-6 ... S-9
EV-10 ... EV-11

Floor 2
C-12 ... C-16
S-17 ... S-20
EV-21 ... EV-22
18. Exception Handler
GlobalExceptionHandler.java
package com.parking.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    public ResponseEntity<?> badRequest(
            RuntimeException exception
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "error",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalError(
            Exception exception
    ) {

        return ResponseEntity
                .internalServerError()
                .body(
                        Map.of(
                                "error",
                                "Internal server error"
                        )
                );
    }
}
19. Frontend

Put these three files inside:

src/main/resources/static/
index.html
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>ParkEase - Parking Management</title>

    <link rel="stylesheet"
          href="style.css">

</head>

<body>

<header>

    <div class="logo">
        🚗 ParkEase
    </div>

    <nav>

        <a href="#home">
            Home
        </a>

        <a href="#dashboard">
            Dashboard
        </a>

        <button onclick="showAuth()">
            Login / Register
        </button>

    </nav>

</header>


<!-- LANDING PAGE -->

<section id="home"
         class="hero">

    <div>

        <p class="tag">
            SMART PARKING MANAGEMENT
        </p>

        <h1>
            Manage every parking spot
            with confidence.
        </h1>

        <p>

            Fast check-in, accurate billing,
            EV charging spots, plate search
            and real-time parking availability.

        </p>

        <a href="#dashboard"
           class="primary">

            Open Dashboard

        </a>

    </div>


    <div class="hero-card">

        <h3>
            Attendant's Day
        </h3>

        <span>
            ✓ Automatic spot allocation
        </span>

        <span>
            ✓ Accurate parking fee
        </span>

        <span>
            ✓ EV-only parking
        </span>

        <span>
            ✓ Plate-number search
        </span>

        <span>
            ✓ Persistent database
        </span>

    </div>

</section>


<!-- FEATURES -->

<section class="features">

    <h2>
        Built for Parking Attendants
    </h2>

    <div class="grid">

        <div>

            <h3>
                Automatic Allocation
            </h3>

            <p>
                Automatically assigns a suitable
                parking spot.
            </p>

        </div>


        <div>

            <h3>
                Accurate Billing
            </h3>

            <p>
                Part-hours are rounded up and
                daily cap is applied.
            </p>

        </div>


        <div>

            <h3>
                Quick Search
            </h3>

            <p>
                Find vehicles quickly using
                license plate number.
            </p>

        </div>

    </div>

</section>


<!-- DASHBOARD -->

<section id="dashboard"
         class="dashboard">

    <h2>
        Parking Dashboard
    </h2>

    <div id="authStatus"
         class="notice">

        Please login to use the dashboard.

    </div>


    <div id="app"
         class="hidden">


        <!-- AVAILABILITY -->

        <h3>
            Current Availability
        </h3>

        <div id="availability"
             class="cards">

        </div>


        <!-- CHECK IN / CHECK OUT -->

        <div class="two">


            <div class="panel">

                <h3>
                    Vehicle Check-In
                </h3>

                <input id="inPlate"
                       placeholder="RJ14AB1234">

                <select id="vehicleType">

                    <option value="CAR">
                        Normal Car
                    </option>

                    <option value="EV">
                        Electric Vehicle
                    </option>

                </select>

                <button onclick="checkIn()">
                    Check In
                </button>

            </div>


            <div class="panel">

                <h3>
                    Vehicle Check-Out
                </h3>

                <input id="outPlate"
                       placeholder="RJ14AB1234">

                <button onclick="checkOut()">
                    Check Out
                </button>

            </div>

        </div>


        <!-- SEARCH -->

        <div class="panel">

            <h3>
                Search Vehicle
            </h3>

            <div class="row">

                <input id="searchPlate"
                       placeholder="Enter plate number">

                <button onclick="searchPlate()">
                    Search
                </button>

            </div>

            <div id="searchResult"></div>

        </div>


        <!-- SPOTS -->

        <div class="panel">

            <h3>
                Parking Spots
            </h3>

            <div id="spots"></div>

        </div>


        <!-- HISTORY -->

        <div class="panel">

            <h3>
                Parking History
            </h3>

            <div class="row">

                <select id="sortBy">

                    <option value="checkIn">
                        Check-in
                    </option>

                    <option value="checkOut">
                        Check-out
                    </option>

                    <option value="fee">
                        Fee
                    </option>

                    <option value="plateNumber">
                        Plate
                    </option>

                    <option value="status">
                        Status
                    </option>

                </select>


                <select id="direction">

                    <option value="desc">
                        Newest
                    </option>

                    <option value="asc">
                        Oldest
                    </option>

                </select>


                <button onclick="loadHistory(0)">
                    Apply
                </button>

            </div>

            <div id="history"></div>

        </div>

    </div>

</section>


<footer>

    ParkEase —
    Smart Parking Management System

</footer>


<!-- LOGIN MODAL -->

<div id="authModal"
     class="modal hidden">

    <div class="modal-box">

        <button class="close"
                onclick="hideAuth()">
            ×
        </button>

        <h2>
            Account
        </h2>

        <input id="name"
               placeholder="Name">

        <input id="email"
               type="email"
               placeholder="Email">

        <input id="password"
               type="password"
               placeholder="Password">

        <div class="row">

            <button onclick="register()">
                Register
            </button>

            <button onclick="login()">
                Login
            </button>

        </div>

        <p id="authMsg"></p>

    </div>

</div>


<script src="app.js"></script>

</body>

</html>
20. CSS
style.css
* {
    box-sizing: border-box;
}

body {

    margin: 0;

    font-family:
        Arial,
        sans-serif;

    background: #f5f7fb;

    color: #18212f;
}


/* HEADER */

header {

    height: 70px;

    background: white;

    display: flex;

    align-items: center;

    justify-content: space-between;

    padding: 0 7%;

    border-bottom:
        1px solid #e5e8ee;

    position: sticky;

    top: 0;

    z-index: 5;
}


.logo {

    font-size: 22px;

    font-weight: 800;
}


nav {

    display: flex;

    align-items: center;

    gap: 20px;
}


nav a {

    text-decoration: none;

    color: #39465a;
}


button,
.primary {

    border: none;

    background: #1f6feb;

    color: white;

    padding: 11px 16px;

    border-radius: 9px;

    cursor: pointer;

    font-weight: 600;

    text-decoration: none;
}


/* HERO */

.hero {

    padding: 80px 7%;

    display: grid;

    grid-template-columns:
        2fr 1fr;

    gap: 40px;

    background: #eef5ff;
}


.tag {

    color: #1f6feb;

    font-size: 12px;

    font-weight: 800;

    letter-spacing: 2px;
}


.hero h1 {

    font-size: 50px;

    line-height: 1.05;

    margin: 15px 0;
}


.hero p {

    font-size: 18px;

    line-height: 1.6;

    max-width: 700px;
}


.hero-card {

    background: white;

    padding: 25px;

    border-radius: 16px;

    box-shadow:
        0 10px 30px #00000010;
}


.hero-card span {

    display: block;

    padding: 13px 0;

    border-bottom:
        1px solid #eee;
}


/* FEATURES */

.features,
.dashboard {

    padding: 55px 7%;
}


.grid {

    display: grid;

    grid-template-columns:
        repeat(3, 1fr);

    gap: 20px;
}


.grid > div {

    background: white;

    padding: 25px;

    border-radius: 15px;

    border:
        1px solid #e4e8ef;
}


/* DASHBOARD */

.cards {

    display: grid;

    grid-template-columns:
        repeat(3, 1fr);

    gap: 15px;
}


.card {

    background: white;

    padding: 20px;

    border-radius: 14px;

    border:
        1px solid #e2e6ed;
}


.card b {

    display: block;

    font-size: 30px;

    margin-top: 7px;
}


.two {

    display: grid;

    grid-template-columns:
        1fr 1fr;

    gap: 20px;

    margin: 20px 0;
}


.panel {

    background: white;

    padding: 24px;

    border-radius: 16px;

    border:
        1px solid #e4e8ef;

    margin: 20px 0;
}


input,
select {

    padding: 12px;

    border:
        1px solid #ccd3df;

    border-radius: 8px;

    margin: 5px;

    width:
        calc(100% - 10px);

    background: white;
}


.row {

    display: flex;

    gap: 8px;

    align-items: center;
}


/* TABLE */

.table {

    width: 100%;

    border-collapse: collapse;
}


.table th,
.table td {

    padding: 11px;

    border-bottom:
        1px solid #e8ebf0;

    text-align: left;
}


.occupied {

    opacity: 0.5;
}


/* NOTICE */

.notice {

    padding: 13px;

    border-radius: 10px;

    background: #fff0d8;

    margin: 15px 0;
}


/* MODAL */

.modal {

    position: fixed;

    inset: 0;

    background: #0008;

    display: grid;

    place-items: center;

    z-index: 10;
}


.modal-box {

    background: white;

    padding: 28px;

    border-radius: 16px;

    width:
        min(420px, 92%);

    position: relative;
}


.modal-box input {

    width: 100%;

    margin: 8px 0;
}


.close {

    position: absolute;

    right: 15px;

    top: 10px;

    background: none;

    color: black;

    font-size: 25px;
}


.hidden {

    display: none !important;
}


/* FOOTER */

footer {

    text-align: center;

    padding: 30px;

    background: #172033;

    color: white;
}


/* MOBILE */

@media(max-width:800px) {

    .hero {

        grid-template-columns: 1fr;
    }

    .hero h1 {

        font-size: 38px;
    }

    .grid,
    .two,
    .cards {

        grid-template-columns: 1fr;
    }

    nav a {

        display: none;
    }

    .row {

        flex-direction: column;

        align-items: stretch;
    }
}
21. JavaScript
app.js
let garageId = 1;


const $ = (id) =>
    document.getElementById(id);


// ================= API HELPER =================

async function api(url, options = {}) {

    const response =
        await fetch(
            url,
            {
                headers: {
                    "Content-Type":
                        "application/json"
                },

                ...options
            }
        );


    const data =
        await response
            .json()
            .catch(() => ({}));


    if (!response.ok) {

        throw new Error(
            data.error ||
            "Request failed"
        );
    }


    return data;
}


// ================= AUTH MODAL =================

function showAuth() {

    $("authModal")
        .classList
        .remove("hidden");
}


function hideAuth() {

    $("authModal")
        .classList
        .add("hidden");
}


// ================= REGISTER =================

async function register() {

    try {

        const data =
            await api(
                "/api/auth/register",
                {
                    method: "POST",

                    body: JSON.stringify({

                        name:
                            $("name").value,

                        email:
                            $("email").value,

                        password:
                            $("password").value
                    })
                }
            );


        $("authMsg").textContent =
            data.message;

    }

    catch (error) {

        $("authMsg").textContent =
            error.message;
    }
}


// ================= LOGIN =================

async function login() {

    try {

        const data =
            await api(
                "/api/auth/login",
                {
                    method: "POST",

                    body: JSON.stringify({

                        email:
                            $("email").value,

                        password:
                            $("password").value
                    })
                }
            );


        localStorage.setItem(
            "user",
            JSON.stringify(data)
        );


        $("authStatus").textContent =
            "Logged in as " +
            data.name;


        $("app")
            .classList
            .remove("hidden");


        hideAuth();

        loadAll();

    }

    catch (error) {

        $("authMsg").textContent =
            error.message;
    }
}


// ================= LOAD ALL =================

async function loadAll() {

    await loadAvailability();

    await loadSpots();

    await loadHistory(0);
}


// ================= AVAILABILITY =================

async function loadAvailability() {

    try {

        const data =
            await api(
                `/api/parking/availability/${garageId}`
            );


        $("availability")
            .innerHTML =

            Object.entries(data)
                .map(
                    ([type, count]) => `

                    <div class="card">

                        <span>
                            ${type} FREE
                        </span>

                        <b>
                            ${count}
                        </b>

                    </div>

                `
                )
                .join("");

    }

    catch (error) {

        console.log(error);
    }
}


// ================= CHECK IN =================

async function checkIn() {

    try {

        const data =
            await api(
                "/api/parking/check-in",
                {
                    method: "POST",

                    body: JSON.stringify({

                        garageId:
                            garageId,

                        plateNumber:
                            $("inPlate").value,

                        vehicleType:
                            $("vehicleType").value

                    })
                }
            );


        alert(
            "Vehicle checked in!\n\n" +
            "Plate: " +
            data.plateNumber +
            "\nSpot: " +
            data.spot.spotNumber
        );


        $("inPlate").value = "";

        loadAll();

    }

    catch (error) {

        alert(error.message);
    }
}


// ================= CHECK OUT =================

async function checkOut() {

    try {

        const data =
            await api(
                "/api/parking/check-out",
                {
                    method: "POST",

                    body: JSON.stringify({

                        plateNumber:
                            $("outPlate").value

                    })
                }
            );


        alert(
            "Checkout successful!\n\n" +
            "Plate: " +
            data.plateNumber +
            "\nFee: ₹" +
            Number(data.fee).toFixed(2)
        );


        $("outPlate").value = "";

        loadAll();

    }

    catch (error) {

        alert(error.message);
    }
}


// ================= SEARCH =================

async function searchPlate() {

    try {

        const plate =
            $("searchPlate").value;


        const data =
            await api(
                `/api/parking/search?plate=${encodeURIComponent(plate)}`
            );


        if (data.length === 0) {

            $("searchResult").innerHTML =
                "<p>No records found.</p>";

            return;
        }


        $("searchResult").innerHTML = `

            <table class="table">

                <tr>

                    <th>Plate</th>
                    <th>Spot</th>
                    <th>Status</th>
                    <th>Fee</th>

                </tr>

                ${data.map(ticket => `

                    <tr>

                        <td>
                            ${ticket.plateNumber}
                        </td>

                        <td>
                            ${ticket.spot.spotNumber}
                        </td>

                        <td>
                            ${ticket.status}
                        </td>

                        <td>
                            ${ticket.fee ?? "-"}
                        </td>

                    </tr>

                `).join("")}

            </table>
        `;

    }

    catch (error) {

        alert(error.message);
    }
}


// ================= SPOTS =================

async function loadSpots() {

    try {

        const data =
            await api(
                `/api/parking/spots/${garageId}`
            );


        $("spots").innerHTML = `

            <table class="table">

                <tr>

                    <th>Floor</th>
                    <th>Spot</th>
                    <th>Type</th>
                    <th>Status</th>

                </tr>

                ${data.map(spot => `

                    <tr
                        class="${
                            spot.occupied
                                ? "occupied"
                                : ""
                        }"
                    >

                        <td>
                            ${spot.floor}
                        </td>

                        <td>
                            ${spot.spotNumber}
                        </td>

                        <td>
                            ${spot.spotType}
                        </td>

                        <td>
                            ${
                                spot.occupied
                                ? "Occupied"
                                : "Free"
                            }
                        </td>

                    </tr>

                `).join("")}

            </table>
        `;

    }

    catch (error) {

        console.log(error);
    }
}


// ================= HISTORY =================

async function loadHistory(page = 0) {

    try {

        const sortBy =
            $("sortBy").value;


        const direction =
            $("direction").value;


        const data =
            await api(
                `/api/parking/history?page=${page}` +
                `&size=10` +
                `&sortBy=${sortBy}` +
                `&direction=${direction}`
            );


        $("history").innerHTML = `

            <table class="table">

                <tr>

                    <th>Plate</th>
                    <th>Spot</th>
                    <th>Check In</th>
                    <th>Check Out</th>
                    <th>Fee</th>
                    <th>Status</th>

                </tr>

                ${data.content.map(ticket => `

                    <tr>

                        <td>
                            ${ticket.plateNumber}
                        </td>

                        <td>
                            ${ticket.spot.spotNumber}
                        </td>

                        <td>
                            ${
                                new Date(
                                    ticket.checkIn
                                ).toLocaleString()
                            }
                        </td>

                        <td>
                            ${
                                ticket.checkOut
                                ? new Date(
                                    ticket.checkOut
                                ).toLocaleString()
                                : "-"
                            }
                        </td>

                        <td>
                            ${
                                ticket.fee ?? "-"
                            }
                        </td>

                        <td>
                            ${ticket.status}
                        </td>

                    </tr>

                `).join("")}

            </table>


            <div class="row">

                <button
                    ${
                        data.first
                        ? "disabled"
                        : ""
                    }

                    onclick="
                        loadHistory(${page - 1})
                    "
                >
                    Previous
                </button>


                <span>

                    Page
                    ${page + 1}
                    of
                    ${Math.max(
                        1,
                        data.totalPages
                    )}

                </span>


                <button
                    ${
                        data.last
                        ? "disabled"
                        : ""
                    }

                    onclick="
                        loadHistory(${page + 1})
                    "
                >
                    Next
                </button>

            </div>
        `;

    }

    catch (error) {

        console.log(error);
    }
}


// ================= AUTO LOGIN =================

window.addEventListener(
    "DOMContentLoaded",
    () => {

        const savedUser =
            localStorage.getItem(
                "user"
            );


        if (savedUser) {

            const user =
                JSON.parse(
                    savedUser
                );


            $("authStatus")
                .textContent =
                "Logged in as " +
                user.name;


            $("app")
                .classList
                .remove("hidden");


            loadAll();
        }

    }
);
22. .gitignore
target/
data/
.idea/
.vscode/
*.iml
.DS_Store
23. README.md
# ParkEase - Smart Parking Management System

## Project Overview

ParkEase is a full-stack parking garage management system designed
for parking attendants.

It manages:

- Vehicle check-in
- Vehicle check-out
- Parking spot allocation
- EV charging spots
- Parking fee calculation
- License plate search
- Parking history
- Pagination
- Sorting
- User registration
- User login

---

## Technology Stack

### Backend

- Java 17
- Spring Boot
- Spring Data JPA
- REST API
- Maven

### Database

- H2
- Persistent file-based database

### Frontend

- HTML
- CSS
- JavaScript

---

## How to Run

Open the project in GitHub Codespaces.

Run:

```bash
mvn spring-boot:run

The application runs on:

http://localhost:8080

Open port 8080 from the Codespaces Ports tab.

Default Garage

The application automatically creates:

2 floors
10 compact spots
8 standard spots
4 EV spots

Pricing:

First hour = ₹50
Each additional hour = ₹30
Daily cap = ₹300
Parking Rules
Normal Car

A normal car gets:

COMPACT → STANDARD

EV spots are not assigned to normal cars.

EV

An EV can only use:

EV

charging spots.

Billing

Part-hours are rounded upward.

Example:

30 minutes → 1 hour
1 hour 10 minutes → 2 hours
2 hours 20 minutes → 3 hours

Fee:

First hour = ₹50

Additional hour = ₹30

Maximum:

₹300 daily cap
REST APIs
Authentication
Register
POST /api/auth/register

Example:

{
  "name": "Krishna",
  "email": "krishna@gmail.com",
  "password": "123456"
}
Login
POST /api/auth/login
Garage
GET /api/garages
POST /api/garages
GET /api/garages/{id}
POST /api/garages/spots
Parking
Check In
POST /api/parking/check-in

Example:

{
  "garageId": 1,
  "plateNumber": "RJ14AB1234",
  "vehicleType": "CAR"
}
Check Out
POST /api/parking/check-out

Example:

{
  "plateNumber": "RJ14AB1234"
}
Availability
GET /api/parking/availability/1
Spots
GET /api/parking/spots/1
Search
GET /api/parking/search?plate=RJ14
Active Ticket
GET /api/parking/active?plate=RJ14AB1234
History
GET /api/parking/history?page=0&size=10&sortBy=checkIn&direction=desc
Database

H2 database is stored in:

./data/parkingdb

H2 Console:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:file:./data/parkingdb

Username:

sa

Password:

empty
Testing Checklist
Register a user.
Login.
Check in a normal car.
Verify compact spot allocation.
Check in EV.
Verify EV spot allocation.
Try parking the same plate twice.
Check availability.
Search by plate.
Check out.
Verify calculated fee.
Verify spot becomes free.
Test pagination.
Test sorting.
Restart application and verify database persistence.
Future Features
Online payment integration
QR-code parking tickets
Live occupancy analytics

---

# 24. `REASONING.md`

```markdown
# REASONING

## 1. Problem Understanding

The parking attendant needs a system that can manage vehicles
entering and leaving a multi-level parking garage.

The important operations are:

- Check vehicle in
- Find an appropriate parking spot
- Prevent double parking
- Check vehicle out
- Calculate correct fee
- Search vehicles
- Monitor available spots

---

# 2. Main Entities

The database contains:

## User

Stores login information.

```text
id
name
email
passwordHash
createdAt
Garage

Stores garage pricing.

id
name
firstHourRate
extraHourRate
dailyCap
ParkingSpot

Stores:

garage
floor
spotNumber
spotType
occupied
Vehicle

Stores:

plateNumber
vehicleType
ParkingTicket

Stores:

plateNumber
vehicleType
spot
checkIn
checkOut
fee
status
3. Spot Allocation

For an EV:

EV → EV spot only

For a normal car:

COMPACT → STANDARD

This prevents normal vehicles from taking EV charging spots.

4. Billing Logic

First calculate parking duration.

Partial hours are rounded upward.

Formula:

hours = (minutes + 59) / 60

For example:

61 minutes

becomes:

2 hours

Then:

fee =
firstHourRate
+
(hours - 1) × extraHourRate

Finally:

fee = minimum(fee, dailyCap)
5. Duplicate Parking Prevention

Before check-in:

Find active ticket using plate number

If an active ticket exists:

Reject check-in

This prevents the same vehicle from occupying
multiple spots.

6. Database Persistence

H2 file mode is used instead of in-memory mode.

Therefore:

Application restart
        ↓
Database remains
        ↓
Parking history remains
7. REST Architecture

The application follows:

Frontend
    ↓
REST Controller
    ↓
Service
    ↓
Repository
    ↓
Database

This separates UI, business logic and database operations.

8. Time Management

For a timed assessment, unnecessary complexity was avoided.

The solution does not use:

Microservices
Kubernetes
Docker
Complex distributed architecture

The priority is:

Database
↓
Check-in
↓
Check-out
↓
Billing
↓
Spot allocation
↓
Search
↓
UI
↓
Pagination / sorting
9. Production Improvements

For a production deployment:

Use PostgreSQL
Use BigDecimal for money
Add JWT/session authentication
Add role-based access
Add database locking for concurrent check-ins
Add automated integration tests
Add payment integration
Add audit logging

---

# 25. `AI_LOGS.md`

For your actual assessment, **do not simply submit a fake AI log**.

Use:

```markdown
# AI LOGS

Paste the complete AI conversation used during the assessment here.

The conversation must be copied exactly as required by the assessment.
Do not modify or rewrite the conversation.

Your assessment specifically says the AI conversation must be pasted as-is, so replace this placeholder with the actual conversation you use during Round 2.

26. Run it in GitHub Codespaces

Once you have created all files, your Codespace terminal should look like:

ParkingGarage/
├── pom.xml
├── README.md
├── REASONING.md
├── AI_LOGS.md
└── src/

Then run:

mvn clean

Then:

mvn spring-boot:run

You should see something similar to:

Tomcat started on port 8080
Started ParkingApplication

Then in Codespaces:

PORTS → 8080 → Open in Browser

You should get:

🚗 ParkEase

Manage every parking spot with confidence.

[Open Dashboard]
27. Test the complete flow
Step 1 — Register
Login / Register
       ↓
Name: Krishna
Email: krishna@gmail.com
Password: 123456
       ↓
Register
Step 2 — Login
Email: krishna@gmail.com
Password: 123456
       ↓
Login
Step 3 — Normal car

Enter:

Plate:
RJ14AB1234

Vehicle:
Normal Car

Click:

Check In

Expected:

Vehicle checked in!

Plate: RJ14AB1234
Spot: C-1
Step 4 — EV

Enter:

Plate:
RJ14EV5678

Vehicle:
Electric Vehicle

Expected:

Vehicle checked in!

Plate: RJ14EV5678
Spot: EV-10

The important interview point is:

An EV is never assigned a compact or standard spot.

Step 5 — Check out

Enter:

RJ14AB1234

Click:

Check Out

The backend calculates:

duration
   ↓
round partial hour upward
   ↓
first hour rate
   ↓
additional hour rate
   ↓
daily cap
   ↓
final fee
Important change from your screenshot

Your current:

ParkingGarage.java

is useful for understanding the core Java logic, but for the actual Builder Round you should not keep everything inside that one console class.

Use:

ParkingGarage
      ↓
Spring Boot
      ↓
REST API
      ↓
Service
      ↓
JPA Repository
      ↓
H2 Database
      ↓
HTML/CSS/JavaScript UI

That directly satisfies the requirements for database + REST APIs + usable UI + authentication + search + pagination + sorting + landing page.

Chat paused until usage resets at 7:14 PM
You’ve reached the limit for chats that include files or images. Start a new text-only chat or upgrade to continue now.
New chat
Claim free offer
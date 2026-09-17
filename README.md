# ParkingGarage

Spring Boot REST application for a multi-level city parking garage.

## Features
- Multi-level compact and standard spots
- Check-in / check-out
- Automatic spot allocation
- Tiered hourly pricing
- Part-hours rounded up
- Daily price cap
- Active and historical sessions
- H2 database
- Simple web UI at `/`
- REST API

## Run
Requirements: Java 17+ and Maven 3.9+.

```bash
mvn spring-boot:run
```
Open `http://localhost:8080/`.

Build:
```bash
mvn clean package
java -jar target/parking-garage-1.0.0.jar
```

## API
- `GET /api/parking/spots`
- `GET /api/parking/spots?type=COMPACT`
- `POST /api/parking/check-in` body `{"vehicleNumber":"RJ14AB1234","type":"STANDARD"}`
- `POST /api/parking/check-out/{sessionId}`
- `GET /api/parking/active`
- `GET /api/parking/sessions`
- `POST /api/auth/register` body `{"username":"admin","password":"1234"}`
- `POST /api/auth/login` body `{"username":"admin","password":"1234"}`

## Default rates
Compact: first hour ₹50, each additional hour ₹30, daily cap ₹500.
Standard: first hour ₹70, each additional hour ₹40, daily cap ₹700.



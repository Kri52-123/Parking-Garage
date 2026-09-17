# REASONING

## Design decisions
1. Spring Boot + Spring Data JPA keeps the project small and easy to run in a timed builder round.
2. Spot and Rate are separate entities because spot availability and pricing are different responsibilities.
3. ParkingSession records entry, exit, assigned spot and final fee.
4. The service layer owns business rules: allocation, duplicate active vehicle prevention, fee calculation and spot release.
5. Fee calculation uses ceiling division: `(minutes + 59) / 60`, so every started hour is charged.
6. The first hour uses the first-hour rate. Remaining hours use the cheaper additional-hour rate. The result is capped at the daily cap.
7. H2 is used for zero-setup local execution. It can later be replaced by PostgreSQL/MySQL by changing configuration.

## Complexity
- Spot lookup is O(n) for the seeded in-memory result list; database indexes can be added for larger deployments.
- Check-in and check-out are O(n) at the service level for selecting the first available spot.

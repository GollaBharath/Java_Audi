/*
Design and implement a ride-hailing simulation named Mini-Uber (RideNow) using Object-Oriented Programming concepts.

Features:
i)Rider requests a ride.
ii)Nearest available driver is assigned.
iii)Trip fare depends on distance and vehicle type.
iv)Rider can rate driver at end of trip.

Entities:
User: id, name, phone
Driver: id, name, vehicleType, isAvailable, rating
Ride: id, rider, driver, startLocation, endLocation, distanceKm, fare, status (REQUESTED/ONGOING/COMPLETED)

Rules / Constraints:
i).A driver can handle only one ongoing ride.
ii).Fare calculation:

 a) Base fare ₹50
 b) ₹10/km for Sedan, ₹8/km for Hatchback, ₹15/km for SUV
iii).After ride, status changes to COMPLETED and driver becomes available again.

Test Scenarios:
1. User requests a ride → nearest available driver assigned.
2. Mark ride as completed → fare computed, driver rating updated.
3. Request new ride → previously occupied driver not reassigned until available.
4. Print driver leaderboard (by rating).

*/

public class Uber {
    public static void main(String[] args) {

    }
}

class User {
    int id;
    String name;
    long phone;

    void RequestRide() {

    }
}

class Driver {
    int id;
    String name;
    String vehicleType;
    Driver driversList[];
    boolean isAvailable;
    int rating;
}

class Ride {
    int id;
    User rider;
    Driver driver;
    String startLocation;
    String endLocation;
    double distance;
    double fare;
    String status;
}
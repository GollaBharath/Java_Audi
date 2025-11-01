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
class User {
    int id;
    String name;
    String phone;

    User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}

class Driver {
    int id;
    String name;
    String vehicleType; // "Sedan", "Hatchback", "SUV"
    boolean isAvailable;
    double rating;
    int totalRatings;
    double x, y; // driver location

    Driver(int id, String name, String vehicleType, double x, double y) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
        this.rating = 0.0;
        this.totalRatings = 0;
        this.x = x;
        this.y = y;
    }

    void addRating(double newRating) {
        rating = (rating * totalRatings + newRating) / (totalRatings + 1);
        totalRatings++;
    }
}

class Ride {
    int id;
    User rider;
    Driver driver;
    double startX, startY;
    double endX, endY;
    double distanceKm;
    double fare;
    String status; // "REQUESTED", "ONGOING", "COMPLETED"

    Ride(int id, User rider, Driver driver, double startX, double startY, double endX, double endY, double distanceKm,
            double fare) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.distanceKm = distanceKm;
        this.fare = fare;
        this.status = "REQUESTED";
    }
}

public class Uber2 {
    static Driver[] drivers = new Driver[5];
    static User[] users = new User[5];
    static Ride[] rides = new Ride[10];
    static int rideCount = 0;

    static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    static double calculateFare(double distance, String vehicleType) {
        double baseFare = 50;
        double rate = 0;
        if (vehicleType.equalsIgnoreCase("Sedan"))
            rate = 10;
        else if (vehicleType.equalsIgnoreCase("Hatchback"))
            rate = 8;
        else if (vehicleType.equalsIgnoreCase("SUV"))
            rate = 15;
        return baseFare + rate * distance;
    }

    static Driver findNearestAvailableDriver(double x, double y) {
        Driver nearest = null;
        double minDist = 9999;
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null && drivers[i].isAvailable) {
                double dist = calculateDistance(x, y, drivers[i].x, drivers[i].y);
                if (dist < minDist) {
                    minDist = dist;
                    nearest = drivers[i];
                }
            }
        }
        return nearest;
    }

    static Ride requestRide(User user, double startX, double startY, double endX, double endY) {
        Driver assigned = findNearestAvailableDriver(startX, startY);
        if (assigned == null)
            return null;

        double distance = calculateDistance(startX, startY, endX, endY);
        double fare = calculateFare(distance, assigned.vehicleType);

        Ride ride = new Ride(++rideCount, user, assigned, startX, startY, endX, endY, distance, fare);
        assigned.isAvailable = false;
        rides[rideCount - 1] = ride;
        ride.status = "ONGOING";
        return ride;
    }

    static void completeRide(Ride ride, double rating) {
        ride.status = "COMPLETED";
        ride.driver.isAvailable = true;
        ride.driver.x = ride.endX;
        ride.driver.y = ride.endY;
        ride.driver.addRating(rating);
    }

    static void printLeaderboard() {
        System.out.println("\\nDriver Leaderboard (by Rating):");
        // simple bubble sort by rating
        for (int i = 0; i < drivers.length - 1; i++) {
            for (int j = i + 1; j < drivers.length; j++) {
                if (drivers[i] != null && drivers[j] != null && drivers[i].rating < drivers[j].rating) {
                    Driver temp = drivers[i];
                    drivers[i] = drivers[j];
                    drivers[j] = temp;
                }
            }
        }

        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null) {
                System.out.printf("%d. %s - %.2f stars (%s)\\n", i + 1, drivers[i].name, drivers[i].rating,
                        drivers[i].vehicleType);
            }
        }
    }

    public static void main(String[] args) {
        // Add drivers
        drivers[0] = new Driver(1, "Ramesh", "Sedan", 0, 0);
        drivers[1] = new Driver(2, "Sita", "Hatchback", 5, 2);
        drivers[2] = new Driver(3, "Karan", "SUV", -2, -1);

        // Add users
        users[0] = new User(101, "Asha", "9999999999");
        users[1] = new User(102, "Vikram", "8888888888");

        // Request first ride
        System.out.println("Asha requests a ride...");
        Ride r1 = requestRide(users[0], 1, 1, 6, 6);
        if (r1 != null) {
            System.out.println("Driver assigned: " + r1.driver.name + " (" + r1.driver.vehicleType + ")");
            System.out.printf("Fare: ₹%.2f\\n", r1.fare);
            completeRide(r1, 4.5);
            System.out.println("Ride completed with rating 4.5\\n");
        }

        // Request second ride
        System.out.println("Vikram requests a ride...");
        Ride r2 = requestRide(users[1], 2, 2, 4, 8);
        if (r2 != null) {
            System.out.println("Driver assigned: " + r2.driver.name);
            System.out.printf("Fare: ₹%.2f\\n", r2.fare);
            completeRide(r2, 5.0);
        } else {
            System.out.println("No driver available!");
        }

        // Show leaderboard
        printLeaderboard();
    }
}
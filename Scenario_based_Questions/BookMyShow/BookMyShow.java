package Scenario_based_Questions.BookMyShow;

import java.time.LocalDate;

public class BookMyShow {

}

class ticket {
    int Sno;
    LocalDate Date;
    double cost;
    String seatNo;
    Customer customer;

    ticket(int Sno,
            LocalDate Date,
            double cost,
            String seatNo,
            Customer customer) {
        this.Sno = Sno;
        this.Date = Date;
        this.cost = cost;
        this.seatNo = seatNo;
        this.customer = customer;
    }
}

abstract class Event {
    String name;
    String venue;
    LocalDate EventDate;
    int rating = 0;
    double cost;
    static boolean[][] slot1Seats;
    static boolean[][] slot2Seats;
    static boolean[][] slot3Seats;
    static Event[] allEvents;

    Event(String name,
            String venue,
            String Date,
            int rating,
            double cost) {
        // int seatsX = 4;
        // int seatsY = 5;
        // for (int i = 0; i < seatsX; i++) {
        // for (int j = 0; j < seatsY; j++) {
        // slot1Seats[i][j] = false;
        // slot2Seats[i][j] = false;
        // slot3Seats[i][j] = false;
        // }
        // }

        String[] d = Date.split(",");
        LocalDate EventDate = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
        this.name = name;
        this.venue = venue;
        this.EventDate = EventDate;
        this.rating = rating;
        this.cost = cost;

    }

    // void book(int slot) {
    // System.out.println("Choose a seat from the available seats :");
    // Scanner scan = new Scanner(System.in);
    // if (slot == 1) {
    // System.out.println(slot1Seats);
    // } else if (slot == 2) {
    // System.out.println(slot2Seats);
    // } else if (slot == 3) {
    // System.out.println(slot3Seats);
    // }

    // scan.close();
    // }

}

class User {
    String name;

    Event[] tickets = {};

    User(String name) {
        this.name = name;
    }

    static void display(String str) {
        System.out.println("---------------------------------");
        System.out.println(str);
        System.out.println("---------------------------------");
    }

    // void bookTicketsOf(Event e) {
    // display("Booking a ticket of the event - " + e.name);
    // System.out.println("Choose a slot : (1/2/3)");
    // Scanner scan = new Scanner(System.in);
    // int slot = scan.nextInt();
    // scan.close();
    // if (slot == 1 || slot == 2 || slot == 3) {
    // e.book(slot);
    // } else {
    // display("Invalid Slot!!!");
    // return;
    // }
    // tickets = Arrays.copyOf(tickets, tickets.length + 1);
    // tickets[tickets.length] = e;
    // }
}

class Customer extends User {
    String email;

    Customer(String name, String email) {
        super(name);
        this.email = email;
    }
}
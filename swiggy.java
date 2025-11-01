/*
Design and implement a simple food delivery simulation named Mini-Swiggy (FoodExpress) using Object-Oriented Programming concepts.

Features (Minimum):
i)Users can browse restaurants.
ii)Each restaurant has multiple dishes.
iii)Users can place an order for multiple dishes.
iv)Orders calculate total cost and estimated delivery time.

Entities:
User: id, name, address, cart[], orderHistory[]
Restaurant: id, name, menu[], location
Dish: id, name, price, category
Order: id, user, restaurant, items[], totalPrice, deliveryTime, status (PLACED / DELIVERED)

Rules / Constraints:
i)Order can only include dishes from one restaurant.
ii)Once delivered, order status becomes DELIVERED and can’t be changed.
iii)Cart clears automatically after placing an order.

Test Scenarios:
1. User adds 3 dishes from same restaurant → places order → total correct.
2. Try mixing dishes from 2 restaurants → reject with message.
3. After delivery, change status → disallowed.
4. View order history → shows all previous orders.



*/
class Dish {
    int id;
    String name;
    double price;
    String category;

    Dish(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

class Restaurant {
    int id;
    String name;
    String location;
    Dish[] menu;

    Restaurant(int id, String name, String location, Dish[] menu) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
    }
}

class Order {
    int id;
    User user;
    Restaurant restaurant;
    Dish[] items;
    double totalPrice;
    int deliveryTime; // in minutes
    String status; // "PLACED" or "DELIVERED"

    Order(int id, User user, Restaurant restaurant, Dish[] items, double totalPrice, int deliveryTime) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.totalPrice = totalPrice;
        this.deliveryTime = deliveryTime;
        this.status = "PLACED";
    }
}

class User {
    int id;
    String name;
    String address;
    Dish[] cart = new Dish[10];
    Order[] orderHistory = new Order[10];
    int cartCount = 0;
    int orderCount = 0;

    User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    void addToCart(Dish dish) {
        if (cartCount < cart.length) {
            cart[cartCount++] = dish;
            System.out.println(dish.name + " added to cart.");
        } else {
            System.out.println("Cart is full!");
        }
    }

    void clearCart() {
        for (int i = 0; i < cart.length; i++) {
            cart[i] = null;
        }
        cartCount = 0;
    }

    void addOrderToHistory(Order order) {
        if (orderCount < orderHistory.length) {
            orderHistory[orderCount++] = order;
        }
    }

    void viewOrderHistory() {
        System.out.println("\nOrder History for " + name + ":");
        for (int i = 0; i < orderCount; i++) {
            Order o = orderHistory[i];
            System.out.println(
                    "Order " + o.id + " from " + o.restaurant.name + " | ₹" + o.totalPrice + " | Status: " + o.status);
        }
    }
}

public class swiggy {
    static Restaurant[] restaurants = new Restaurant[5];
    static int orderCounter = 0;

    static void browseRestaurants() {
        System.out.println("\nAvailable Restaurants:");
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i] != null) {
                System.out.println((i + 1) + ". " + restaurants[i].name + " - " + restaurants[i].location);
                for (int j = 0; j < restaurants[i].menu.length; j++) {
                    Dish d = restaurants[i].menu[j];
                    System.out.println("   " + d.id + ") " + d.name + " - ₹" + d.price + " (" + d.category + ")");
                }
            }
        }
    }

    static Order placeOrder(User user) {
        if (user.cartCount == 0) {
            System.out.println("Cart is empty!");
            return null;
        }

        // Check if all dishes are from the same restaurant
        Restaurant selectedRestaurant = null;
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i] != null) {
                for (int j = 0; j < restaurants[i].menu.length; j++) {
                    if (restaurants[i].menu[j] == user.cart[0]) {
                        selectedRestaurant = restaurants[i];
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < user.cartCount; i++) {
            Dish current = user.cart[i];
            boolean found = false;
            for (int j = 0; j < selectedRestaurant.menu.length; j++) {
                if (current == selectedRestaurant.menu[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Cannot mix dishes from multiple restaurants!");
                return null;
            }
        }

        // Calculate total
        double total = 0;
        for (int i = 0; i < user.cartCount; i++) {
            total += user.cart[i].price;
        }

        int deliveryTime = 30; // fixed example value
        Order newOrder = new Order(++orderCounter, user, selectedRestaurant, user.cart.clone(), total, deliveryTime);
        user.addOrderToHistory(newOrder);
        user.clearCart();

        System.out.println("Order placed successfully! Total = ₹" + total);
        return newOrder;
    }

    static void deliverOrder(Order order) {
        if (order.status.equals("DELIVERED")) {
            System.out.println("Order already delivered. Status cannot be changed.");
        } else {
            order.status = "DELIVERED";
            System.out.println("Order " + order.id + " delivered successfully!");
        }
    }

    public static void main(String[] args) {
        // Create restaurants and dishes
        Dish[] menu1 = {
                new Dish(1, "Paneer Butter Masala", 180, "Main Course"),
                new Dish(2, "Butter Naan", 40, "Bread"),
                new Dish(3, "Gulab Jamun", 60, "Dessert")
        };

        Dish[] menu2 = {
                new Dish(4, "Veg Burger", 120, "Fast Food"),
                new Dish(5, "French Fries", 70, "Snack"),
                new Dish(6, "Coke", 50, "Beverage")
        };

        restaurants[0] = new Restaurant(1, "Punjabi Dhaba", "Hyderabad", menu1);
        restaurants[1] = new Restaurant(2, "Burger Point", "Hyderabad", menu2);

        // Create user
        User u1 = new User(101, "Asha", "Banjara Hills");

        // Test 1: Add 3 dishes from same restaurant and place order
        System.out.println("\n--- Test 1: Same restaurant order ---");
        browseRestaurants();
        u1.addToCart(menu1[0]);
        u1.addToCart(menu1[1]);
        u1.addToCart(menu1[2]);
        Order o1 = placeOrder(u1);
        if (o1 != null)
            System.out.println("Order Total: ₹" + o1.totalPrice);

        // Test 2: Try mixing dishes from 2 restaurants
        System.out.println("\n--- Test 2: Mixed restaurant order ---");
        u1.addToCart(menu1[0]);
        u1.addToCart(menu2[0]); // from another restaurant
        Order o2 = placeOrder(u1);
        if (o2 == null)
            u1.clearCart(); // clear cart manually if rejected

        // Test 3: After delivery, trying to change status again
        System.out.println("\n--- Test 3: Delivery and status check ---");
        deliverOrder(o1);
        deliverOrder(o1); // attempt again

        // Test 4: View order history
        System.out.println("\n--- Test 4: Order History ---");
        u1.viewOrderHistory();
    }
}
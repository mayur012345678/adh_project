package user;


import com.model.Movie;
import com.model.Snacks;
import com.service.BookMyShow;
import com.service.BookingDesk;

import java.util.*;
import java.util.Map.Entry;

public class Test {
	
	 private static Map<String, String> users = new HashMap<>();

	    static {
	        users.put("1", "1");
	        users.put("user2", "password2");
	        
	    }
	
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookMyShow desk = new BookingDesk();
        Map<Integer, List<Object>> bookingDetails = new HashMap<>();
        
        
        String userID, password;
        

        
        boolean authenticated = true;
        do {
            System.out.print("Enter your user ID: ");
            userID = scanner.next();
            System.out.print("Enter your password: ");
            password = scanner.next();

            if (authenticate(userID, password)) {
                authenticated = true;
                System.out.println("Authentication successful. Access granted.");
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        } while (!authenticated);
      
        int bookingId = 1; // Initialize the booking ID
        int choice;

        do {
            System.out.println("BookMyShow - Main Menu");
            System.out.println("1. Book Movie Ticket");
            System.out.println("2. Add Snacks to Booking");
            System.out.println("3. View Booking Details with Total Price");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Movie selectedMovie = desk.bookTicket(createMovieData());
                    System.out.println(selectedMovie.getName() + "selected movie has been booked.");
                    List<Object> bookingInfo = new ArrayList<>();
                    bookingInfo.add(selectedMovie);
                    bookingDetails.put(bookingId, bookingInfo);
                    bookingId++;
                    break;
                case 2:
                    List<Snacks> selectedSnacks = desk.addMeal(createSnackData());
                    System.out.println("Selected snacks have been added to your booking.");
                    bookingDetails.get(bookingId - 1).addAll(selectedSnacks);
                    break;
                case 3:
                    viewBookingDetails(bookingDetails);
                    break;
                case 4:
                    System.out.println("You have successfully logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (choice != 4);
    }

    private static boolean authenticate(String userID, String password) {
        return users.containsKey(userID) && users.get(userID).equals(password);
    }

	private static List<Movie> createMovieData() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Naruto", 250, 4.5f, "Action"));
        movies.add(new Movie(2, "Demon Slayer", 200, 4.0f, "Action"));
        
        return movies;
    }

    private static List<Snacks> createSnackData() {
        List<Snacks> snacks = new ArrayList<>();
        snacks.add(new Snacks(1, "Popcorn", "Classic popcorn", 50));
        snacks.add(new Snacks(2, "Soda", "Soft drink", 30));
        
        return snacks;
    }

    private static void viewBookingDetails(Map<Integer, List<Object>> bookingDetails) {
        double totalPrice = 0.0;
        for (Entry<Integer, List<Object>> entry : bookingDetails.entrySet()) {
            System.out.println("Booking ID: " + entry.getKey());

            List<Object> details = entry.getValue();
            for (Object detail : details) {
                if (detail instanceof Movie) {
                    Movie movie = (Movie) detail;
                    System.out.println("Movie: " + movie.getName() + " (Price: Rs" + movie.getPrice() + ")");
                    totalPrice += movie.getPrice();
                } else if (detail instanceof Snacks) {
                    Snacks snacks = (Snacks) detail;
                    System.out.println("Snacks: " + snacks.getName() + " (Price: Rs" + snacks.getPrice() + ")");
                    totalPrice += snacks.getPrice();
                }
            }
            System.out.println("----------------------");
        }
        System.out.println("Total Price: Rs" + totalPrice);
    }
}

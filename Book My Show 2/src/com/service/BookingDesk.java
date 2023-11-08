package com.service;


import com.model.Movie;
import com.model.Snacks;

import java.util.*;

public class BookingDesk implements BookMyShow {
    private Scanner scanner = new Scanner(System.in);
    private Map<Integer, List<Object>> bookingDetails = new HashMap<>();
    private int bookingId = 1; 

    @Override
    public Movie bookTicket(List<Movie> mv) {
        System.out.println("Available Movies:");
        for (int i = 0; i < mv.size(); i++) {
            System.out.println(i + 1 + ". " + mv.get(i).getName());
        }

        System.out.print("Select a movie by entering its number: ");
        int movieChoice = scanner.nextInt();

        if (movieChoice >= 1 && movieChoice <= mv.size()) {
            Movie selectedMovie = mv.get(movieChoice - 1);
            System.out.println(selectedMovie.getName() + " has been booked.");
            List<Object> bookingInfo = new ArrayList<>();
            bookingInfo.add(selectedMovie);
            bookingDetails.put(bookingId, bookingInfo);
            bookingId++;
            return selectedMovie;
        } else {
            System.out.println("Invalid movie selection.");
            return null;
        }
    }

    @Override
    public List<Snacks> addMeal(List<Snacks> sn) {
        System.out.println("Available Snacks:");
        for (int i = 0; i < sn.size(); i++) {
            System.out.println(i + 1 + ". " + sn.get(i).getName());
        }

        System.out.print("Select snacks by entering their numbers : ");
        String snacksChoices = scanner.next(); 
        String[] selectedSnackNumbers = snacksChoices.split(",");
        List<Snacks> selectedSnacksList = new ArrayList<>();

        for (String snackNumber : selectedSnackNumbers) {
            int selectedSnackIndex = Integer.parseInt(snackNumber) - 1;

            if (selectedSnackIndex >= 0 && selectedSnackIndex < sn.size()) {
                selectedSnacksList.add(sn.get(selectedSnackIndex));
            } else {
                System.out.println("Invalid selection: " + (selectedSnackIndex + 1));
            }
        }
        System.out.println("thanks for ordering snacks.");
        bookingDetails.get(bookingId - 1).addAll(selectedSnacksList);
        return selectedSnacksList;
    }

    @Override
    public void printDetails(Map<Integer, List<Object>> m) {
        double totalPrice = 0.0;
        for (Map.Entry<Integer, List<Object> > entry : m.entrySet()) {
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
        System.out.println("**************** Thanks for visiting ******************");
    }
    

    @Override
    public void logOut() {
        System.out.println("You have successfully logged out.");
    }
}

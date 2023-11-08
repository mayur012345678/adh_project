package com.service;
import com.model.Movie;
import com.model.Snacks;

import java.util.List;
import java.util.Map;

public interface BookMyShow {
    Movie bookTicket(List<Movie> mv);
    List<Snacks> addMeal(List<Snacks> sn);
    void printDetails(Map<Integer, List<Object>> m);
    void logOut();
}


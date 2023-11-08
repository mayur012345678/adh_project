package com.model;

public class Movie {
	 private int id;
	    private String name;
	    private int price;
	    private float rating;
	    private String genre;

	    public Movie(int id, String name, int price, float rating, String genre) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.rating = rating;
	        this.genre = genre;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		
		

}

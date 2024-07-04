package com.packt.booklibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	private int isbn, originalPublicationYear;
	private String authors, title, languageCode, imageUrl;
	private float averageRating, ratingsCount;
	
	public Book() {}
	
	public Book(int bookId, int isbn, String authors, int originalPublicationYear, String title, String languageCode, float averageRating, float ratingsCount, String imageUrl) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.authors = authors;
		this.originalPublicationYear = originalPublicationYear;
		this.title = title;
		this.languageCode = languageCode;
		this.averageRating = averageRating;
		this.ratingsCount = ratingsCount;
		this.imageUrl = imageUrl;
	}

	
	// Getters and Setters
	public long getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	public void setOriginalPublicationYear(int originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public float getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(float ratingsCount) {
		this.ratingsCount = ratingsCount;
	}
}

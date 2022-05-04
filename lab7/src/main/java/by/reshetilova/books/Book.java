package by.reshetilova.books;

public class Book extends Papers{
    String author;
    Genre genre;

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public Book(String name, Double price, String author, Genre genre) {
        super(name, price);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString(){
        return super.toString() + " Author: " + author + " Genre: " + genre.getGenre();
    }
}

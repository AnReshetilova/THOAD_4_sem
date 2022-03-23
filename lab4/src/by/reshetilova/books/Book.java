package by.reshetilova.books;

public class Book extends Papers{
    String author;
    Genre genre = Genre.DETECTIVE;

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setAuthor(String val) {author = val;}

    public void setGenre(String val) {genre.setGenre(val);}

    public Book(){}

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

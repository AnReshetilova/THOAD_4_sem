package by.reshetilova.books;

public class Magazine extends Papers{
    String publisher;

    public Magazine(){}

    public void setPublisher(String val) {publisher = val;}

    public Magazine(String name, Double price, String publisher) {
        super(name, price);
        this.publisher = publisher;
    }

    @Override
    public String toString(){
        return super.toString() + " Publisher: " + publisher;
    }
}

package by.reshetilova.books;

public class Card extends Papers{
    String holiday;

    public Card(String name, Double price, String holiday) {
        super(name, price);
        this.holiday = holiday;
    }

    @Override
    public String toString(){
        return super.toString() + " Holiday: " + holiday;
    }
}

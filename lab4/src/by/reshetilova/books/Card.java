package by.reshetilova.books;

public class Card extends Papers{
    String holiday;

    public Card(){}

    public Card(String name, Double price, String holiday) {
        super(name, price);
        this.holiday = holiday;
    }

    public void setHoliday(String val) {holiday = val;}
    @Override
    public String toString(){
        return super.toString() + " Holiday: " + holiday;
    }
}

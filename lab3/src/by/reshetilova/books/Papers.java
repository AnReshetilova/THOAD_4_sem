package by.reshetilova.books;

import org.apache.log4j.Logger;

public abstract class Papers {
    private static final Logger LOG = Logger.getLogger(Papers.class);

    String name;
    Double price;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Papers() {
    }

    public Papers(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Price: " + price;
    }
}

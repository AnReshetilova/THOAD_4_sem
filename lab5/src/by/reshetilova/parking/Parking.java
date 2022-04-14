package by.reshetilova.parking;

public class Parking {
    private final int id;
    private final int size;

    public Parking(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}

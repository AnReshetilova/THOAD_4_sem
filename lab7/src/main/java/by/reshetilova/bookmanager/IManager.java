package by.reshetilova.bookmanager;

import by.reshetilova.bookstore.Bookstore;

public interface IManager {
    Bookstore generateBookstore(Bookstore bookstore, int booksCount)throws Exception;
    Double booksPrice(Bookstore bookstore);
    public Bookstore sortByPrice(Bookstore bookstore);
    public Bookstore findByPrice(Bookstore bookstore, int lower, int upper)throws Exception;
}

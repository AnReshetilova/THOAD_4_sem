package by.reshetilova.bookmanager;

import by.reshetilova.bookstore.Bookstore;

public interface IManager {
    Bookstore generateBookstore(Bookstore bookstore, int booksCount, int cardsCount, int magazinesCount)throws Exception;
    int booksPrice(Bookstore bookstore);
    public Bookstore sortByPrice(Bookstore bookstore);
    public Bookstore findByPrice(Bookstore bookstore, int lower, int upper)throws Exception;
}

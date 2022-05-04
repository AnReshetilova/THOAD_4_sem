package by.reshetilova.bookmanager;

import by.reshetilova.books.*;
import by.reshetilova.bookstore.Bookstore;

import java.util.*;


public class BookManager implements IManager {

    private ArrayList<Book> basicBooks = new ArrayList<>();

    {
        basicBooks.add(new Book("Martin Eden", 100d, "Jack London", Genre.NOVEL));
        basicBooks.add(new Book("And then there were none", 150d, "Agatha Christie", Genre.DETECTIVE));
        basicBooks.add(new Book("Jonathan Strange and Mr Norrell", 200d, "Susanna Clarke", Genre.FANTASY));
        basicBooks.add(new Book("11.22.63", 160d, "Stephen King", Genre.THRILLER));
    }

    public Bookstore generateBookstore(Bookstore bookstore, int booksCount)
            throws Exception {
        Random rnd = new Random();
        while (booksCount != 0) {
            bookstore.add(basicBooks.get(rnd.nextInt(0, 4)));
            booksCount--;
        }

        return bookstore;
    }

    public Double booksPrice(Bookstore bookstore) {
        Double count = 0d;
        for (var el : bookstore.getPaperList()) {
            count += el.getPrice();
        }

        return count;
    }

    Comparator<Papers> compare = new Comparator<Papers>() {
        @Override
        public int compare(Papers o1, Papers o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    };

    public Bookstore sortByPrice(Bookstore bookstore) {
        bookstore.getPaperList().sort(compare);
        return bookstore;
    }

    public Bookstore findByPrice(Bookstore bookstore, int lower, int upper) throws Exception {
        Bookstore bookstoreRes = new Bookstore();
        for (var el : bookstore.getPaperList()) {
            if (el.getPrice() > lower && el.getPrice() < upper) {
                bookstoreRes.add(el);
            }
        }

        return bookstoreRes;
    }
}

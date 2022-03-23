package by.reshetilova.bookmanager;

import by.reshetilova.books.*;
import by.reshetilova.bookstore.Bookstore;
import org.apache.log4j.Logger;

import java.util.*;


public class BookManager implements IManager {
    private static final Logger LOG = Logger.getLogger(BookManager.class);

    private ArrayList<Book> basicBooks = new ArrayList<>();
    private ArrayList<Card> basicCards = new ArrayList<>();
    private ArrayList<Magazine> basicMagazins = new ArrayList<>();

    {
        basicBooks.add(new Book("Martin Eden", 100d, "Jack London", Genre.NOVEL));
        basicBooks.add(new Book("And then there were none", 150d, "Agatha Christie", Genre.DETECTIVE));
        basicBooks.add(new Book("Jonathan Strange and Mr Norrell", 200d, "Susanna Clarke", Genre.FANTASY));
        basicBooks.add(new Book("11.22.63", 160d, "Stephen King", Genre.THRILLER));

        basicCards.add(new Card("High", 11d, "Halloween"));
        basicCards.add(new Card("Low", 11d, "Easter"));
        basicCards.add(new Card("Medium", 11d, "New Year"));
        basicCards.add(new Card("Well done", 11d, "Birthday"));

        basicMagazins.add((new Magazine("Girls", 1d, "OOOSTAR")));
        basicMagazins.add((new Magazine("Boys", 1d, "OOOMOON")));
        basicMagazins.add((new Magazine("Dogs", 1d, "OOOCLOUD")));
        basicMagazins.add((new Magazine("Cats", 1d, "OOOSUPERNOVA")));
    }

    // сгенерировать аэропорт---------------------------------------
    public Bookstore generateBookstore(Bookstore bookstore, int booksCount, int cardsCount, int magazinesCount)
            throws Exception {
        Random rnd = new Random();
        while (booksCount != 0) {
            bookstore.add(basicBooks.get(rnd.nextInt(0, 4)));
            booksCount--;
        }

        while (cardsCount != 0) {
            bookstore.add(basicCards.get(rnd.nextInt(0, 4)));
            cardsCount--;
        }

        while (magazinesCount != 0) {
            bookstore.add(basicMagazins.get(rnd.nextInt(0, 4)));
            magazinesCount--;
        }

        LOG.info("generate bookstore...");
        return bookstore;
    }

    // емкость пассажирских-----------------------------------------
    public int booksPrice(Bookstore bookstore) {
        int count = 0;
        for (var el : bookstore.getPaperList()) {
            if (el instanceof Book) {
                count++;
            }
        }

        LOG.info("get books price...");
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

        LOG.info("find by price...");
        return bookstoreRes;
    }


}

package by.reshetilova.bookmanager;

import by.reshetilova.books.*;
import by.reshetilova.bookstore.Bookstore;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class BookManager implements IManager {
    private static final Logger LOG = Logger.getLogger(BookManager.class);
    Comparator<Papers> compare = new Comparator<Papers>() {
        @Override
        public int compare(Papers o1, Papers o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    };
    private final ArrayList<Book> basicBooks = new ArrayList<>();
    private final ArrayList<Card> basicCards = new ArrayList<>();
    private final ArrayList<Magazine> basicMagazins = new ArrayList<>();

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

    public ArrayList<Book> getBasicBooks() {
        return basicBooks;
    }

    public void generateBookstore(Bookstore bookstore, int booksCount, int cardsCount, int magazinesCount)
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
    }

    public void generateBookstore(Bookstore bookstore)
            throws Exception {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("D:\\вуз\\4 сем\\THOAD_4_sem\\lab4\\files\\info.xml"));

        Papers paper = null;
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "Book":
                        paper = new Book();
                        break;
                    case "Card":
                        paper = new Card();
                        break;
                    case "Magazine":
                        paper = new Magazine();
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        paper.setName(nextEvent.asCharacters().getData());
                        break;
                    case "price":
                        nextEvent = reader.nextEvent();
                        paper.setPrice(Double.parseDouble(nextEvent.asCharacters().getData()));
                        break;
                    case "author":
                        nextEvent = reader.nextEvent();
                        Book book = (Book) paper;
                        book.setAuthor(nextEvent.asCharacters().getData());
                        paper = book;
                        break;
                    case "genre":
                        nextEvent = reader.nextEvent();
                        book = (Book) paper;
                        book.setGenre(nextEvent.asCharacters().getData());
                        paper = book;
                        break;
                    case "holiday":
                        nextEvent = reader.nextEvent();
                        Card card = (Card) paper;
                        card.setHoliday(nextEvent.asCharacters().getData());
                        paper = card;
                        break;
                    case "publisher":
                        nextEvent = reader.nextEvent();
                        Magazine magazine = (Magazine) paper;
                        magazine.setPublisher(nextEvent.asCharacters().getData());
                        paper = magazine;
                        break;
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                switch (endElement.getName().getLocalPart()) {
                    case "Book":
                    case "Magazine":
                    case "Card":
                        bookstore.add(paper);
                }
            }
        }
    }

    public Double booksPrice(Bookstore bookstore) {
        Double price = bookstore.getPaperList().stream().filter(el -> el instanceof Book).mapToDouble(o->o.getPrice()).sum();
        LOG.info("get books price...");
        return price;
    }

    public Bookstore sortByPrice(Bookstore bookstore) {
        bookstore.getPaperList().sort(compare);
        return bookstore;
    }

    public Bookstore findByPrice(Bookstore bookstore, int lower, int upper) throws Exception {
        var bookstoreRes = bookstore.getPaperList().stream().filter(el -> el.getPrice() > lower && el.getPrice() < upper);
        Bookstore res = new Bookstore();
        res.setPaperList(bookstoreRes.toList());

        LOG.info("find by price...");
        return res;
    }
}

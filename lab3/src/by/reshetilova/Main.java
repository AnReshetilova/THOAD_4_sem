package by.reshetilova;

import by.reshetilova.bookmanager.BookManager;
import by.reshetilova.bookstore.Bookstore;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {
    public static void main(String[] args) throws Exception {
        LOG.info("Starting program_____________________________");

        var bookstore1 = new Bookstore();
        var bookstore2 = new Bookstore();

        var bookManager = new BookManager();
        bookManager.generateBookstore(bookstore1, 2,3,4);
        bookManager.generateBookstore(bookstore2, 2, 2, 2);

        for (var el: bookstore1.getPaperList()){
            System.out.println(el.toString());
        }
        System.out.println();

        for (var el: bookstore2.getPaperList()){
            System.out.println(el.toString());
        }

        System.out.println("\n" + bookManager.booksPrice(bookstore1) + "\n");

        for(var el: bookManager.findByPrice(bookstore1, 20, 200).getPaperList())
        {
            System.out.println(el.toString());
        }

    }

    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);

}

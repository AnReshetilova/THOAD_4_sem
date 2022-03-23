package by.reshetilova;

import by.reshetilova.bookmanager.BookManager;
import by.reshetilova.books.Book;
import by.reshetilova.books.Genre;
import by.reshetilova.bookstore.Bookstore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    static {
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }

    public static void main(String[] args) throws Exception {
        LOG.info("Starting program_____________________________");

        var bookstore1 = new Bookstore();
        var bookstore2 = new Bookstore();

        var bookManager = new BookManager();
        bookManager.generateBookstore(bookstore1, 2, 3, 4);
        bookManager.generateBookstore(bookstore2);

        System.out.println("\nBookstore1");
        for (var el : bookstore1.getPaperList()) {
            System.out.println(el.toString());
        }
        System.out.println("\nBookstore2");

        for (var el : bookstore2.getPaperList()) {
            System.out.println(el.toString());
        }

        System.out.println("\nBooks price");
        System.out.println("\n" + bookManager.booksPrice(bookstore1) + "\n");

        System.out.println("\nFind by price");
        for (var el : bookManager.findByPrice(bookstore1, 20, 200).getPaperList()) {
            System.out.println(el.toString());
        }

        System.out.println("\nSerializing...");
        Book books = new Book("jsonbook", 115d, "jsonauthor", Genre.FANTASY);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(books);
        System.out.println(json);
        FileOutputStream jsonOut = new FileOutputStream("D:\\вуз\\4 сем\\THOAD_4_sem\\lab4\\files\\info.json");
        byte[] outText = json.getBytes(StandardCharsets.UTF_8);
        jsonOut.write(outText, 0, outText.length);
        LOG.info("JSON serialized");

        System.out.println("\nDeserializing...");
        Scanner scanner = new Scanner(new File("D:\\вуз\\4 сем\\THOAD_4_sem\\lab4\\files\\info.json"));
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext())
            result.append(scanner.nextLine());
        scanner.close();
        Book jsonBook = gson.fromJson(result.toString(), Book.class);
        System.out.println(jsonBook.toString());
        LOG.info("JSON deserialized");
    }

}

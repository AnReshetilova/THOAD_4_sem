package by.reshetilova;

import by.reshetilova.bookmanager.BookManager;
import by.reshetilova.bookstore.Bookstore;

public class Main {
    public static void main(String[] args) throws Exception {

        var bookstore1 = new Bookstore();

        var bookManager = new BookManager();
        bookManager.generateBookstore(bookstore1, 4);

        for (var el: bookstore1.getPaperList()){
            System.out.println(el.toString());
        }
        System.out.println();

        System.out.println("\n" + bookManager.booksPrice(bookstore1) + "\n");

        for(var el: bookManager.findByPrice(bookstore1, 20, 200).getPaperList())
        {
            System.out.println(el.toString());
        }
    }
}

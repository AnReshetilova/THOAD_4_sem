package by.reshetilova.tests;

import by.reshetilova.bookmanager.BookManager;
import by.reshetilova.books.Book;
import by.reshetilova.books.Genre;
import by.reshetilova.bookstore.Bookstore;
import org.testng.Assert;
import org.testng.annotations.*;

public class BookManagerTest {

    BookManager manager = new BookManager();
    Bookstore bookstore = new Bookstore();

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("@AfterMethod");
    }

    @BeforeGroups
    public void beforeGroups() throws Exception {
        System.out.println("BeforeGroups");
    }

    @AfterGroups
    public void afterGroups() throws Exception {
        System.out.println("@AfterGroups");
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        System.out.println("@BeforeClass");
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        System.out.println("@BeforeTest");
        double price = 10;
        while (price > 0) {
            bookstore.add(new Book(Double.toString(price), price, "", Genre.FANTASY));
            price--;
        }
    }

    @AfterTest
    public void afterTest() throws Exception {
        System.out.println("@AfterTest");
    }

    @AfterClass
    public void afterClass() throws Exception {
        System.out.println("@AfterClass");
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        System.out.println("@BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        System.out.println("@AfterSuite");
    }

    @Test (groups = {"uint"})
    public void testBooksPrice() throws Exception{
        System.out.println("testBooksPrice");
        Assert.assertEquals((Double)55d, manager.booksPrice(bookstore));
    }

    @Test (timeOut = 1000, groups = {"uint"})
    public void testFindByPrice() throws Exception {
        System.out.println("testFindByPrice");
        Bookstore result = new Bookstore();
        result.add(bookstore.getPaperList().get(1));
        result.add(bookstore.getPaperList().get(2));

        Assert.assertTrue(result.equals(manager.findByPrice(bookstore, 7, 10)));
    }

    @Test (enabled = false)
    public void testGenerateBookstore() throws Exception {
        System.out.println("testGenerateBookstore");
        Assert.assertEquals(4, manager.generateBookstore(new Bookstore(), 4).getPaperList().size());
    }
}

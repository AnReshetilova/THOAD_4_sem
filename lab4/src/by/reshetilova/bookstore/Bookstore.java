package by.reshetilova.bookstore;

import by.reshetilova.books.Papers;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private List<Papers> paperList;

    public Bookstore() {
        paperList = new ArrayList<>();
    }

    public Bookstore(ArrayList<Papers> paperList) {
        this.paperList = paperList;
    }

    public List<Papers> getPaperList() {
        return this.paperList;
    }

    public void setPaperList(List<Papers> paperList) {
        this.paperList = paperList;
    }

    public boolean add(Papers item) throws Exception {
        try{
            this.paperList.add((item));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(Papers item) throws Exception{
        try{
            this.paperList.remove((item));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        String info = "";
        for (var el: paperList) {
            info += el.getName();
        }

        return info;
    }
}
package by.reshetilova.bookstore;

import by.reshetilova.books.Papers;
import java.util.ArrayList;

public class Bookstore {
    private ArrayList<Papers> paperList;

    public Bookstore() {
        paperList = new ArrayList<>();
    }

    public Bookstore(ArrayList<Papers> paperList) {
        this.paperList = paperList;
    }

    public ArrayList<Papers> getPaperList() {
        return this.paperList;
    }

    public void setPaperList(ArrayList<Papers> paperList) {
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

    @Override
    public boolean equals(Object o){
        if (o instanceof Bookstore) {

            for (var e : ((Bookstore) o).getPaperList()) {
                if (!this.getPaperList().contains(e)) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }
}
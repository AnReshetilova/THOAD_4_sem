package by.reshetilova.books;

public enum Genre {
    FANTASY("fantasy"), THRILLER("thriller"), DETECTIVE("detective"), NOVEL("novel");

    public String getGenre() {
        return genre;
    }
    public void setGenre(String g) {
        this.genre = g;
    }

    private String genre;
    Genre(String g){
        this.genre = g;
    }
    public String getCode(){ return genre;}
}


package by.reshetilova.books;

public enum Genre {
    FANTASY, THRILLER, DETECTIVE, NOVEL;

    public String getGenre() {
        return switch (this) {
            case FANTASY -> "fantasy";
            case THRILLER -> "thriller";
            case DETECTIVE -> "detective";
            case NOVEL -> "novel";
        };
    }
}


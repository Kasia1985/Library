public class Book {
    String title;
    String author;
    int releaseDate;
    int pages;
    String publisher;
    String isbn;

    Book(String title, String author, int releaseDate, int pages, String publisher, String bookIsbn){
        this(title,author,releaseDate,pages,publisher);
        isbn = bookIsbn;
    }

    Book(String title, String author, int releaseDate, int pages, String publisher){
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.pages = pages;
        this.publisher = publisher;
    }

    void printInfo() {
        String info = title + ", " + author + ", " +releaseDate + ", " +pages + ", " +publisher + ", " +isbn;
        System.out.println(info);
    }
}

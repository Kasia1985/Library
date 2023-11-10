package pl.javastart.library.io;

import pl.javastart.library.model.Book;

import java.util.Scanner;

public class DataReader {

    private Scanner sc = new Scanner(System.in);

    public Book readAndCreateBook() {
        System.out.println("Title:");
        String title = sc.nextLine();
        System.out.println("Author:");
        String author = sc.nextLine();
        System.out.println("Publisher:");
        String publisher = sc.nextLine();
        System.out.println("ISBN:");
        String isbn = sc.nextLine();
        System.out.println("Publication date:");
        int releaseDate = sc.nextInt();
        sc.nextLine();
        System.out.println("number of pages:");
        int pages = sc.nextInt();
        sc.nextLine();
        return new Book(title, author, releaseDate, pages, publisher,isbn);
    }
}

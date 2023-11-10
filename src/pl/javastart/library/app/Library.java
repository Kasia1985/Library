package pl.javastart.library.app;

import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;

import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        final String appName = "Library v0.8";
        Scanner sc = new Scanner(System.in);

        Book[] books = new Book[1000];
        DataReader dataReader = new DataReader();

        books[0] = dataReader.readAndCreateBook();
        books[1] = dataReader.readAndCreateBook();
        sc.close();

        System.out.println(appName);
        System.out.println("Ksiazki dostepne w bibliotece:");

        books[0].printInfo();
        books[1].printInfo();

    }
}

package pl.javastart.library.app;

import pl.javastart.library.model.Book;

public class Library {
    public static void main(String[] args) {

        final String appName = "Library v0.7";
        Book[] books = new Book[1000];

        books[1] = new Book("\"W pustyni i w puszczy\"","Henryk Sienkiewicz",
                2010, 345,"Greg","732988462872682");

        books[2] = new Book("Java. Efektywne programowanie. Wydanie II", "Joshua Bloch",
                2009, 352, "Helion",
                "9788324620845");

        books[3] = new Book("SCJP Sun Certified Programmer for Java 6 Study Guide", "Bert Bates, Katherine Sierra",
                2008, 851, "McGraw-Hill Osborne Media");

        System.out.println(appName);
        System.out.println("Ksiazki dostepne w bibliotece:");

        books[1].printInfo();
        books[2].printInfo();
        books[3].printInfo();
    }
}

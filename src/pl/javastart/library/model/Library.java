package pl.javastart.library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNumber;
    Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public void addBook(Book book) {
        if (publicationsNumber < MAX_PUBLICATIONS) {
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("The maximum number of books has been reached");
        }
    }

    public void printBooks() {
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book) {
                publications[i].printInfo();
                countBooks++;
            }
        }
        if (countBooks == 0) {
            System.out.println("no books in the library");
        }
    }


    public void addMagazine(Magazine magazine) {
        if (publicationsNumber < MAX_PUBLICATIONS) {
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        } else {
            System.out.println("The maximum number of magazines has been reached");
        }
    }

    public void printMagazines() {
        int countMagazine = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine) {
                publications[i].printInfo();
                countMagazine++;
            }
        }
        if (countMagazine == 0) {
            System.out.println("no magazines in the library");
        }
    }
}


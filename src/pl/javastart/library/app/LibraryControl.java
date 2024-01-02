package pl.javastart.library.app;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.exception.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManager;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Imported data from a file");
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            printer.printLine("A new database has been initiated.");
            library = new Library();
        }
    }

    void controlLoop() {
        Option option;
        do {
            printOption();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Wrong command, re-enter");
            }
        } while (option != Option.EXIT);
    }


    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("A value has been entered that is not a number");
            }
        }
        return option;
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Data export to file successfully completed");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("End of program");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);

    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create magazine, incorrect data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached and another magazine cannot be added");
        }
    }

    private void deleteMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine))
                printer.printLine("magazine removed");
            else
                printer.printLine("no specified magazine");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to delete the magazine, incorrect data");
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book, incorrect data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached and another book cannot be added");
        }
    }

    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book))
                printer.printLine("book removed");
            else
                printer.printLine("no specified book");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to delete the book, incorrect data");
        }
    }

    private void printOption() {
        printer.printLine("Choose option:");
        for (Option option : Option.values()) {
            System.out.println(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "exit program"),
        ADD_BOOK(1, "add new Book"),
        ADD_MAGAZINE(2, "add new Magazine"),
        PRINT_BOOKS(3, "show available books"),
        PRINT_MAGAZINES(4, "show available magazine"),
        DELETE_BOOK(5, "delete book"),
        DELETE_MAGAZINE(6, "delete magazine");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option with ID " + option);

            }
        }
    }
}

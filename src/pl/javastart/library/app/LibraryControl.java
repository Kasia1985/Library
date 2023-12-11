package pl.javastart.library.app;

import pl.javastart.library.app.exception.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();

    public void controlLoop() {
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
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create magazine, incorrect data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached and another magazine cannot be added");
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book, incorrect data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached and another book cannot be added");
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
        PRINT_MAGAZINES(4, "show available magazine");

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
            } catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("No option with ID "+ option);

            }
        }
    }
}

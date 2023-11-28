package pl.javastart.library.app;

import pl.javastart.library.app.exception.NoSuchOptionException;

public enum Option {
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

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
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

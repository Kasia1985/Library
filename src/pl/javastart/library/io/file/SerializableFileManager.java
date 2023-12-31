package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Library)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("No File " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("file reading error " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("incompatible data type in the file " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {

        try (var fos = new FileOutputStream(FILE_NAME);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No File " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("error writing data to file " + FILE_NAME);
        }

    }
}

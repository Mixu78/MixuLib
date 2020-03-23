package com.github.mixu78.mixulib.helpers;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    public boolean writeFile(@Nonnull File file, @Nonnull String content) {
        FileWriter writer;
        try { writer = new FileWriter(file); } catch (IOException e) { return false; }
        try { writer.write(content); } catch (IOException e) { return false; }
        finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {}
        }
        return true;
    }

    public boolean appendFile(@Nonnull File file, @Nonnull String content) {
        FileWriter writer;
        try { writer = new FileWriter(file, true); } catch (IOException e) { return false; }
        try { writer.append(content); } catch (IOException e) { return false; }
        finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {}
        }
        return true;
    }

    public boolean createFile(@Nonnull String path) {
        File file = new File(path);
        if (file.exists()) { return true; }
        else {try {return file.createNewFile();} catch (IOException e) {}}
        return false;
    }

    public boolean createFile(@Nonnull File file) {
        if (file.exists()) { return true; }
        else {try {return file.createNewFile();} catch (IOException e) {}}
        return false;
    }

    public boolean deleteFile(@Nonnull String path) {
        File file = new File(path);
        return file.delete();
    }

    public boolean deleteFile(@Nonnull File file) {
        return file.delete();
    }
}

package ru.redguy.smartdownloader.datasources;

import ru.konstanteam.lokpackager.files.FilesUnPackager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OutJar {
    List<String> dataPacks;
    File pathToSave;
    public boolean print;

    public OutJar(File pathToSave, List<String> dataPacks) {
        this.dataPacks = dataPacks;
        this.pathToSave = pathToSave;
    }

    public boolean unpack() {
        for (String dataPack : dataPacks) {
            FilesUnPackager filesUnPackager = null;
            try {
                filesUnPackager = new FilesUnPackager(new File(dataPack),pathToSave);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                System.exit(-1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(print) System.out.println(new File(dataPack).getName());
            try {
                assert filesUnPackager != null;
                filesUnPackager.unpack();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

package ru.redguy.smartdownloader.datasources;

import ru.konstanteam.lokpackager.files.FilesUnPackager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OutJar {
    List<String> dataPacks;
    Path pathToSave;
    public boolean print;

    public OutJar(Path pathToSave, List<String> dataPacks) {
        this.dataPacks = dataPacks;
        this.pathToSave = pathToSave;
    }

    public boolean unpack() {
        for (String dataPack : dataPacks) {
            FilesUnPackager filesUnPackager = null;
            try {
                filesUnPackager = new FilesUnPackager(new File(dataPack),new File(pathToSave.toString()));
            } catch (FileNotFoundException e) {
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

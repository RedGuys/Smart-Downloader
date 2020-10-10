package ru.redguy.smartdownloader.datasources;

import ru.konstanteam.lokpackager.files.FilesUnPackager;
import ru.redguy.smartdownloader.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class InJar {
    List<String> dataPacks;
    Path pathToSave;
    public boolean print;

    public InJar(Path pathToSave, List<String> dataPacks) {
        this.dataPacks = dataPacks;
        this.pathToSave = pathToSave;
    }

    public boolean unpack() {
        for (String dataPack : dataPacks) {
            FilesUnPackager filesUnPackager = new FilesUnPackager(new BufferedInputStream(Main.class.getResourceAsStream("/"+dataPack)),pathToSave.toString());
            if(print) System.out.println(new File(dataPack).getName());
            try {
                filesUnPackager.unpack();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

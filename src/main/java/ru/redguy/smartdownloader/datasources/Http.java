package ru.redguy.smartdownloader.datasources;

import ru.konstanteam.lokpackager.files.FilesUnPackager;
import ru.redguy.rednetworker.clients.http.ApacheFluentAPI;
import ru.redguy.rednetworker.clients.http.ApacheFluentAPIResponse;
import ru.redguy.rednetworker.clients.http.exceptions.HttpProtocolException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Http {
    List<String> dataPacks;
    File pathToSave;
    public boolean print;

    public Http(File pathToSave, List<String> dataPacks) {
        this.dataPacks = dataPacks;
        this.pathToSave = pathToSave;
    }

    public boolean unpack() throws HttpProtocolException, IOException {
        for (String dataPack : dataPacks) {
            ApacheFluentAPIResponse response = new ApacheFluentAPI().url(dataPack).execute();
            FilesUnPackager filesUnPackager = new FilesUnPackager(new BufferedInputStream(response.getInputStream()),pathToSave.getAbsolutePath());
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

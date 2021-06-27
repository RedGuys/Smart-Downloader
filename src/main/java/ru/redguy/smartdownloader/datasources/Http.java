package ru.redguy.smartdownloader.datasources;

import okhttp3.*;
import okhttp3.Request.Builder;
import ru.konstanteam.lokpackager.files.FilesUnPackager;

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

    public boolean unpack() throws IOException {
        OkHttpClient client = new OkHttpClient();
        for (String dataPack : dataPacks) {
            Request request = new Request.Builder().url(dataPack).build();
            Response response = client.newCall(request).execute();
            FilesUnPackager filesUnPackager = new FilesUnPackager(new BufferedInputStream(response.body().byteStream()),pathToSave.getAbsolutePath());
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

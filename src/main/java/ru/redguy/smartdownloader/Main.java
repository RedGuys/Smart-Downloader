package ru.redguy.smartdownloader;

import ru.redguy.smartdownloader.datasources.Http;
import ru.redguy.smartdownloader.datasources.InJar;
import ru.redguy.smartdownloader.datasources.OutJar;
import ru.redguy.smartdownloader.utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        File path = null;
        switch (config.getUnpackType()) {
            case RunDir:
                path = new File(".");
                break;
            case RunSubDir:
            case FullPath:
                path = new File(config.directory());
                if(config.IsClearBefore()) {
                    FileUtils.deleteDirectory(path);
                }
                if(config.getCreateDirectory()) {
                    path.mkdirs();
                }
                break;
        }
        switch (config.getDataType()) {
            case InJar:
                InJar injar = new InJar(path,config.getDataPacks());
                if(config.getIsPrint()) injar.print = true;
                injar.unpack();
                break;
            case OutJar:
                OutJar outJar = new OutJar(path, config.getDataPacks());
                if(config.getIsPrint()) outJar.print = true;
                outJar.unpack();
                break;
            case Http:
                Http http = new Http(path,config.getDataPacks());
                if(config.getIsPrint()) http.print = true;
                http.unpack();
                break;
        }
    }
}

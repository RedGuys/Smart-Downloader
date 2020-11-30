package ru.redguy.smartdownloader;

import ru.redguy.smartdownloader.datasources.InJar;
import ru.redguy.smartdownloader.datasources.OutJar;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Config config = new Config();
        File path = null;
        switch (config.getUnpackType()) {
            case RunDir:
                path = new File(".");
                break;
            case RunSubDir:
            case FullPath:
                path = new File(config.directory());
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
        }
    }
}

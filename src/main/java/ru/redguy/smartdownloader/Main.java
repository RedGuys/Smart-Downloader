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
        Path path;
        if(args.length == 0) {
            path = Paths.get("").toAbsolutePath();
            if(config.getCreateDirectory()) path = Paths.get(path.toString(),config.directoryName());
        } else {
            path = Paths.get(args[0]).toAbsolutePath();
        }
        try {
            Files.createDirectories(path);
        } catch (IOException ignored) {}
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

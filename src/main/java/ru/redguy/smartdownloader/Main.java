package ru.redguy.smartdownloader;

import ru.redguy.smartdownloader.datasources.InJar;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Config config = new Config();
        Path path;
        if(args.length == 0) {
            path = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String pathStr = path.toAbsolutePath().toString();
            path = Paths.get(pathStr.substring(0,pathStr.length()-4)+"/").toAbsolutePath();
        } else {
            path = Paths.get(args[0]).toAbsolutePath();
        }
        switch (config.getDataType()) {
            case InJar:
                InJar injar = new InJar(path,config.getDataPacks());
                if(config.getIsPrint()) injar.print = true;
                injar.unpack();
                break;
            case OutJar:

        }
    }
}

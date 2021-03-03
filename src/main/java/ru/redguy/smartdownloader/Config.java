package ru.redguy.smartdownloader;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.redguy.smartdownloader.enums.DataType;
import ru.redguy.smartdownloader.enums.UnpackType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private JSONObject json;

    public Config() {
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/config.json");
            if(inputStream == null) throw new IOException("config not found!");
            json = new JSONObject(IOUtils.toString(new InputStreamReader(inputStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataType getDataType() {
        switch (json.getInt("dataType")) {
            case 1:
                return DataType.InJar;
            case 2:
                return DataType.OutJar;
            case 3:
                return DataType.Http;
        }
        return null;
    }

    public UnpackType getUnpackType() {
        switch (json.getInt("unpackType")) {
            case 1:
                return UnpackType.RunDir;
            case 2:
                return UnpackType.RunSubDir;
            case 3:
                return UnpackType.FullPath;
        }
        return null;
    }

    public List<String> getDataPacks() {
        List<String> res = new ArrayList<>();
        JSONArray jsonArray = json.getJSONArray("DataPacks");
        for (Object o : jsonArray) {
            res.add((String) o);
        }
        return res;
    }

    public boolean getIsPrint() {
        return json.getBoolean("isPrint");
    }

    public boolean getCreateDirectory() { return json.getBoolean("createDirectory"); }

    public String directory() { return json.getString("directory"); }

    public boolean IsClearBefore() {
        return json.getBoolean("clearBefore");
    }
}

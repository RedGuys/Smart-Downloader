package ru.redguy.smartdownloader;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.redguy.smartdownloader.enums.DataType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private JSONObject json;

    //TODO: Unpack types: in this folder, with new folder, to selected folder(with/without deleting all from target folder)
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
        switch (json.getInt("type")) {
            case 1:
                return DataType.InJar;
            case 2:
                return DataType.OutJar;
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

    public String directoryName() { return json.getString("directoryName"); }
}

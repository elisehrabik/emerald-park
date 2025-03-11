package edu.kirkwood.shared;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

import java.io.StringReader;

// This class takes JSON and converts it into a Java Json Object
public class MyDecoder implements Decoder.Text<MyJson> {
    @Override
    public MyJson decode(String s) throws DecodeException {
        if(willDecode(s)) {
            JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
            return new MyJson(jsonObject);
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        try {
            Json.createReader(new StringReader(s)).readObject();
            return true;
        } catch(JsonException e) {
            return false;
        }
    }
}

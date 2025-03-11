package edu.kirkwood.shared;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

// This class takes a Java Json Object and converts it to JSON
public class MyEncoder implements Encoder.Text<MyJson> {
    @Override
    public String encode(MyJson object) throws EncodeException {
        return object.toString();
    }
}

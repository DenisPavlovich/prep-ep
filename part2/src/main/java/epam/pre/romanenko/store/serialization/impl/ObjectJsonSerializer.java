package epam.pre.romanenko.store.serialization.impl;

import com.google.gson.Gson;
import epam.pre.romanenko.store.serialization.Serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ObjectJsonSerializer implements Serializer {

    private Gson gson;
    private String fileName;
    private Type typeOfT;

    public ObjectJsonSerializer(String fileName, Type typeOfT) {
        gson = new Gson();

        this.fileName = fileName;
        this.typeOfT = typeOfT;
    }

    @Override
    public void write(Object obj) throws IOException {
        String json = gson.toJson(obj);
        Files.deleteIfExists(Paths.get(fileName));
        Files.write(Paths.get(fileName), json.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);
    }

    @Override
    public <T> T read() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(data, typeOfT);
    }

}

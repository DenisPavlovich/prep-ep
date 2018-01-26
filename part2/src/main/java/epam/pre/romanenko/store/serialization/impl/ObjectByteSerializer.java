package epam.pre.romanenko.store.serialization.impl;

import epam.pre.romanenko.store.serialization.Serializer;

import java.io.*;

public class ObjectByteSerializer implements Serializer {

    private String filePath;

    public ObjectByteSerializer(String fileName) {
        this.filePath = fileName;
    }

    @Override
    public void write(Object obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
        }
    }

    @Override
    public <T> T read() throws IOException, ClassNotFoundException {
        Object result;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            result = ois.readObject();
        }
        return (T) result;
    }

}

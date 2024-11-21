package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;

    public SavedList(File file) {
        this.file = file;
    }

    @Override
    public E get(int index) {
        if(!file.exists()) {
            return null;
        }

        ArrayList<E> list = loadFromFile(file);
        if(list == null)
            return null;

        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        if(!file.exists()) {
            return null;
        }

        ArrayList<E> list = loadFromFile(file);
        if(list == null)
            return null;

        E result = list.set(index, element);
        saveToFile(list, file);
        return result;
    }

    @Override
    public int size() {
        if(!file.exists()) {
            return 0;
        }

        ArrayList<E> list = loadFromFile(file);
        if(list == null)
            return 0;

        return list.size();
    }

    @Override
    public void add(int index, E element) {
        ArrayList<E> list;
        if (!file.exists() || (list = loadFromFile(file)) == null) {
            list = new ArrayList<>();
        }

        list.add(index, element);
        saveToFile(list, file);
    }

    @Override
    public E remove(int index) {
        if(!file.exists()) {
            return null;
        }

        ArrayList<E> list = loadFromFile(file);
        if(list == null)
            return null;

        E result = list.remove(index);
        saveToFile(list, file);
        return result;
    }

    private ArrayList<E> loadFromFile(File file) {
        try(InputStream inputStream = new FileInputStream(file)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                return (ArrayList<E>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Класс не найден.", e);
            }
        } catch (IOException ignored) {
            return null;
        }
    }

    private void saveToFile(ArrayList<E> list, File file) {
        try {
            file.createNewFile();

            try (OutputStream outputStream = new FileOutputStream(file)) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                    objectOutputStream.writeObject(list);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранения.", e);
        }
    }
}
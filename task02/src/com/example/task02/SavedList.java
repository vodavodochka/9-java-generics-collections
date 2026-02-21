package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Objects;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private AbstractList<E> list = new ArrayList<>();
    private final File file;

    public SavedList(File file) {
        this.file = file;
        readFromFile();
    }

    @Override
    public E get(int index) {
        readFromFile();
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E newList =  list.set(index, element);
        writeToFile();
        return newList;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        writeToFile();
        return removedElement;
    }

    public void readFromFile() {
        ArrayList<E> loadList;
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            loadList = (ArrayList<E>) inputStream.readObject();
            this.list.clear();
            this.list.addAll(loadList);
        }
        catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void writeToFile() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            outputStream.writeObject(list);
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }

}

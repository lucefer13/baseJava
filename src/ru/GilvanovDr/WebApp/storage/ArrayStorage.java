package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addNewResume(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillEmptyItem(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

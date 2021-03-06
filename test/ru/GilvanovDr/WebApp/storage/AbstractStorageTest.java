package ru.GilvanovDr.WebApp.storage;


import org.junit.Before;
import org.junit.Test;
import ru.GilvanovDr.WebApp.Config;
import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.GilvanovDr.WebApp.storage.ResumeTestData.*;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public abstract class AbstractStorageTest {
    static final String STORAGE_DIR = Config.get().getStorageDir();
    private static final String UUID_1 = "9ad88dac-e9b8-4753-afc6-771c441c7ffd";

    private Storage storage;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);

    }


    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, "Name1"));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }


    @Test(expected = NoExistStorageException.class)
    public void deleteNoExist() {
        storage.delete("dummy");
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test

    public void update() {
        Resume newResume = new Resume(UUID_1, "new Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
//        storage.update(new Resume("dummy"));
    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NoExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1, "Name1"));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAll() {
        List<Resume> result = storage.getAllSorted();
        assertEquals(3, result.size());
        assertEquals(RESUME_1, result.get(0));
        assertEquals(RESUME_2, result.get(1));
        assertEquals(RESUME_3, result.get(2));


    }
}
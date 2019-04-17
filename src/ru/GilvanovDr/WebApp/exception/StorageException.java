package ru.GilvanovDr.WebApp.exception;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }
}
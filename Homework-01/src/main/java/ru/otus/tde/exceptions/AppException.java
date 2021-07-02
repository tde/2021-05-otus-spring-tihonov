package ru.otus.tde.exceptions;

/**
 * Исключение кидается в случае ошибки чтения вопросов из заданного источника
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

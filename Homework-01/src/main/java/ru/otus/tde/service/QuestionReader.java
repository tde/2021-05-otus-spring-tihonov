package ru.otus.tde.service;

import ru.otus.tde.domain.Question;

import java.util.List;

/**
 * Чтение вопросов из заданного источника
 */
public interface QuestionReader {
    /**
     * @return список объектов с вопросами
     */
    List<Question> read();
}

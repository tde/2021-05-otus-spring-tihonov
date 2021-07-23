package ru.otus.tde.service;

import ru.otus.tde.domain.Question;

import java.util.List;

/**
 * Вывод списка вопросов в заданное место
 */
public interface QuestionWriter {
    /**
     * Напечать на заданное устройство весь список вопросов
     * @param questionList список вопросов
     */
    void printAll(List<Question> questionList);

    /**
     * Вывести один вопрос
     * @param question текстовое содержание вопроса
     */
    void print(Question question);
}

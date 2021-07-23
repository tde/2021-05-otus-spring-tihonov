package ru.otus.tde.service;

import ru.otus.tde.domain.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionWriterConsole implements QuestionWriter {
    private final static Logger log = Logger.getLogger(QuestionWriterConsole.class.getName());

    @Override
    public void printAll(List<Question> questionList) {
        System.out.println(String.format("%s%s%s", System.lineSeparator(), "Список все вопросов:", System.lineSeparator()));
        questionList.forEach(System.out::println);

        System.out.println(String.format("%s", System.lineSeparator()));
    }

    /**
     * Вывести один вопрос
     * @param question текстовое содержание вопроса
     */
    @Override
    public  void print(Question question) {
        System.out.println(question);
    }
}

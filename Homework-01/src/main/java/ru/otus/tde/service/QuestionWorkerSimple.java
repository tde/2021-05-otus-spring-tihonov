package ru.otus.tde.service;

import ru.otus.tde.domain.Question;

import java.util.List;

/**
 * Простейшая реализация интерфеса по обработке вопросника
 *  чтение / вывод / анализ
 */
public class QuestionWorkerSimple implements  QuestionWorker {
    private final QuestionReader questionReader;
    private final QuestionWriter questionWriter;

    public QuestionWorkerSimple(QuestionReader questionReader, QuestionWriter questionWriter) {
        this.questionReader = questionReader;
        this.questionWriter = questionWriter;
    }
    @Override
    public void perform() {
        //прочитать все вопросы из источника
        List<Question> questionList = questionReader.read();

        //отобразить все вопросы
        questionWriter.printAll(questionList);
    }
}

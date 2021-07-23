package ru.otus.tde.domain;

import ru.otus.tde.exceptions.AppException;

public enum QuestionTypeEnum {
    //произволный текстовый ответ
    FREESTYLE(1),

    //выбор одного ответа из нескольких вариантов
    CHOICE_ONE(2),

    //выбор нескольких ответов из нескольких вариантов
    CHOICE_MANY(3);

    public final int id;

    private QuestionTypeEnum(int id) {
        this.id = id;
    }

    public static QuestionTypeEnum getTypeById(int id) {
        /*
        if (ordinary < 1 || ordinary > QuestionTypeEnum.values().length) {
            throw new AppException("Ошибочный определения типа вопроса по индексу: " + ordinary);
        }
        return QuestionTypeEnum.values()[ordinary - 1];
        */
        for (QuestionTypeEnum e : QuestionTypeEnum.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new AppException("Ошибочный определения типа вопроса по индексу: " + id);
    }
}

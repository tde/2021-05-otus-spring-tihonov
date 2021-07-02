package ru.otus.tde.domain;

import ru.otus.tde.exceptions.AppException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  Сущность - один вопрос
 *   содержит текст вопроса и тип вопрос
 */
public class Question {
    private final String text;
    private final QuestionTypeEnum type;
    private final List<String> availableAnswer;

    /**
     * @param text Вопрос в текстовом виде
     * @param type тип вопроса. 1 - предполагает ответ в произвольной тестовой форме, 2 - один из предложенных вариантов
     * @param availableAnswer опционально, список с возможными ответами
     */
    public Question(String text, QuestionTypeEnum type, List<String> availableAnswer) {
        this.text = text;
        this.type = type;
        this.availableAnswer = availableAnswer;
    }

    public String getText() {
        return text;
    }

    public QuestionTypeEnum getType() {
        return type;
    }

    public String toString() {
        if (type == QuestionTypeEnum.FREESTYLE) {
            return String.format("%s? (type yor answer):", text);
        }
        else if (type == QuestionTypeEnum.CHOICE_ONE) {
            return String.format("%s? (choose one from: %s):", text, choiceToString());
        }
        else if (type == QuestionTypeEnum.CHOICE_MANY) {
            return String.format("%s? (choose some from: %s):", text, choiceToString());
        }

        throw new AppException("Unformatted type: " + type);
    }

    private String choiceToString() {
        if (availableAnswer == null) {
            throw new AppException("List of choices not defined for question with type = " + type);
        }

       return IntStream.range(0, availableAnswer.size())
                .mapToObj(index -> String.format("%s[%d]", availableAnswer.get(index), index))
                .collect(Collectors.joining(" / " ));
    }
}

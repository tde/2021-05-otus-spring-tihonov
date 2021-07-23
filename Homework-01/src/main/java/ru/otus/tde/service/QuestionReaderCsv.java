package ru.otus.tde.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.tde.domain.Question;
import ru.otus.tde.domain.QuestionTypeEnum;
import ru.otus.tde.exceptions.AppException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Чтение списка вопросов из CSV-файла
 * файл должен находится в resource, имя файла задается в application.properties
 */
public class QuestionReaderCsv implements QuestionReader {
    private final static Logger log = Logger.getLogger(QuestionReaderCsv.class.getName());
    private final static String CSV_DELIMITER = ";";
    private String csvFileName;

    @Override
    public List<Question> read() {
        log.info("Reading list of question from file: " + csvFileName);
        Resource resource = new ClassPathResource(csvFileName);

        List<Question> questionList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new InputStreamReader(resource.getInputStream()))) {
            while (scanner.hasNextLine()) {
                questionList.add(parseRecord(scanner.nextLine()));
            }

            return questionList;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AppException("Ошибка чтения csv-файла с вопросами", e);
        }
    }

    /*
     * Парсинг одной строки в объект
     */
    private Question parseRecord(String line) {
        String[] fields = line.split(CSV_DELIMITER);

        //Должно быть минимум 2 поля (сам вопрос, его тип и опционально: список выбора)
        if (fields.length < 2) {
            throw new AppException("Ошибочная запись с вопросом, должно быть минимум два поля: " + line);
        }
        try {
            int type = Integer.parseInt(fields[1].trim());
            QuestionTypeEnum qt = QuestionTypeEnum.getTypeById(type);
            List<String> choices = null;

            //Если ответ предполагает выбор из нескольких  вариантов, то надо определеить варианты ответа
            if (qt != QuestionTypeEnum.FREESTYLE) {
                if (fields.length < 3 || fields[2] == null) {
                    throw new AppException("Для выбора ответа из нескольких вариантов требуется указать эти варианты");
                }
                String[] answ = fields[2].trim().split("/");
                choices = List.of(answ);
            }

            return new Question(fields[0], qt, choices);
        }
        catch (NumberFormatException ex) {
            throw new AppException("Ошибочная преобразования типа вопроса в число: " + fields[1]);
        }

    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }
}

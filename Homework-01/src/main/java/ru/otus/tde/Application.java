package ru.otus.tde;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tde.exceptions.AppException;
import ru.otus.tde.service.*;

public class Application {

    public static void main(String[] args) throws AppException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        //Получить Service для обработки вопросника
        QuestionWorker questionWorkerService = context.getBean(QuestionWorkerSimple.class);

        //Выполнить обработку
        questionWorkerService.perform();
    }
}

package com.kodilla.carsbackend.scheduler;

import com.kodilla.carsbackend.config.AdminConfig;
import com.kodilla.carsbackend.domain.Mail;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.repository.CarRepository;
import com.kodilla.carsbackend.service.SimpleMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleMailService simpleMailService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "RentCarApi: Once a day email";

    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        String infoAboutHowManyRented = " rented cars";
        long size = carRepository.retrieveCarsWithState(State.RENTED.name());
        if (size == 1) {
            infoAboutHowManyRented = " rented car";
        }
        simpleMailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT,
                "Currently in database you got: " + size + infoAboutHowManyRented
        ));
    }
}

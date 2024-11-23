package com.capgemini.wsb.fitnesstracker.notification;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RaportGenerator {
    private final TrainingService trainingService;

    private final EmailSender emailSender;
    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateRaport() {

        System.out.println("Initiated report!");
        List<Training> allTrainings = trainingService.getTrainingsFromCurrentMonth();

        String content = allTrainings.toString();
        emailSender.send(new EmailDto(
                "test@example.com",
                "Scheduled Report",
                content
        ));
        System.out.println("Scheduled report email sent!");
    }
}

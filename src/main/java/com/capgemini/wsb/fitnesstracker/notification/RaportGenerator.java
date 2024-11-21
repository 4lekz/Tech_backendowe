package com.capgemini.wsb.fitnesstracker.notification;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RaportGenerator {
private final TrainingProvider trainingProvider;

private final EmailSender emailSender;

    @Scheduled(cron="0 0 0 1 * ?")

    public void generateRaport() {
        List<Training> allTrainings =trainingProvider.getAllTrainings();
    }

}

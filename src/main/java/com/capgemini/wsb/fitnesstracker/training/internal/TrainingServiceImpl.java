package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserService userService;
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }


    @Override
    public List<Training> getUserTraining(Long userId) {
        return trainingRepository.findByUser_Id(userId);
    }

    @Override
    public List<Training> getCompletedTraining(Date endDate) {
        return trainingRepository.findByEndTimeAfter(endDate);
    }

    @Override
    public List<Training> getTrainingByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }
    @Override
    public List<Training> getFinishedTrainingsAfter(Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    @Override
    public Training updateTraining(Long trainingId, TrainingDto trainingDto) {
        Training training = trainingRepository.findById(trainingId).orElse(null);
        Long userId = trainingDto.getUserId();
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found for ID: " + userId);
        }
        training.setUser(user);
        training.setStartTime(trainingDto.getStartTime());
        training.setEndTime(trainingDto.getEndTime());
        training.setActivityType(trainingDto.getActivityType());
        training.setDistance(trainingDto.getDistance());
        training.setAverageSpeed(trainingDto.getAverageSpeed());

        return trainingRepository.save(training);
    }
    @Override
    public Training createTraining(TrainingDto trainingDto) {
        Long userId = trainingDto.getUserId();


        User user = userService.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Training training = new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );

        return trainingRepository.save(training);
    }
}

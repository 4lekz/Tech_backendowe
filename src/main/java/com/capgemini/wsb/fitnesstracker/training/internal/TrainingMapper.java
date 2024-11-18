package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

TrainingDto toDto(Training training) {
    UserDto userDto = new UserDto(
            training.getUser().getId(),
            training.getUser().getFirstName(),
            training.getUser().getLastName(),
            training.getUser().getBirthdate(),
            training.getUser().getEmail()
    );
    Long userId = (training.getUser() != null) ? training.getUser().getId() : null;

    TrainingDto trainingDto = new TrainingDto(
            training.getId(),
            training.getUserId(),
            userDto,
            training.getStartTime(),
            training.getEndTime(),
            training.getActivityType(),
            training.getDistance(),
            training.getAverageSpeed()
    );
    return trainingDto;
}
    Training toEntity(TrainingDto trainingDto, User user) {
        return new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
//    Training toEntity(TrainingDto trainingDto) {
//        User user = new User(
//                trainingDto.getUser().firstName(),
//                trainingDto.getUser().lastName(),
//                trainingDto.getUser().birthdate(),
//                trainingDto.getUser().email()
//        );
//
//        Training training = new Training(
//                user,
//                trainingDto.getStartTime(),
//                trainingDto.getEndTime(),
//                trainingDto.getActivityType(),
//                trainingDto.getDistance(),
//                trainingDto.getAverageSpeed()
//        );
//
//        return training;
    }
}



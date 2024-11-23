package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;



    @GetMapping
    public List<TrainingDto> findAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/{userId}")
    public List<TrainingDto> findTrainingByUserId(@PathVariable Long userId) {
        return trainingService.getUserTraining(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/currentMonth")
    public List<Training> getTrainingsFromCurrentMonth() {
        return trainingService.getTrainingsFromCurrentMonth();
    }
    @GetMapping("/completed")
    public List<TrainingDto> getCompletedTraining(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getCompletedTraining(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getAllFinishedTrainingsAfterTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date afterTime) {
        return trainingService.getFinishedTrainingsAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingService.createTraining(trainingDto);
        return trainingMapper.toDto(training);
    }


    @PutMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto findTrainingById(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(trainingId, trainingDto);
        return trainingMapper.toDto(updatedTraining);
    }

    }




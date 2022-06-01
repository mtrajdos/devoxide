package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.Stage;
import com.fdmgroup.festivalBookingSystem.service.BandService;
import com.fdmgroup.festivalBookingSystem.service.FestivalService;
import com.fdmgroup.festivalBookingSystem.service.StageService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StageTest {

    @Autowired
    private FestivalService festivalService;
	
    @Autowired
    private StageService stageService;

    @Autowired
    private BandService bandService;

    @Test
    void test_ThatAStageCanBeCreated() {
        Stage stage = new Stage("SunnyD");
        stageService.save(stage);
        assertNotNull(stage);
    }

    @Test
    void test_ThatABandCanBeAddedToAStage() {
        Stage stage = new Stage("The Red Bull Stage");
        stageService.save(stage);
        stage.getBandsInStage().add(bandService.findByBand_id(1l).get());
        stageService.save(stage);
        assertNotNull(stage.getBandsInStage());
    }
    
    @Test
    void test_ThatAStageCanBeFoundById() {
    	Optional<Stage> foundStage = stageService.findById(1L);
    	assertFalse(foundStage.isEmpty());
    }
    
    @Test
    void test_ThatAFestivalCanBeFoundUsingStageIdInThatFestival() {
    	long foundFestivalId = stageService.findFestival_Id(1L);
    	Optional<Festival> foundFestival = festivalService.findById(foundFestivalId);
    	assertFalse(foundFestival.isEmpty());
    }

}
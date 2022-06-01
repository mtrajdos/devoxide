package com.fdmgroup.festivalBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Stage;
import com.fdmgroup.festivalBookingSystem.repository.StageDao;
import com.fdmgroup.festivalBookingSystem.repository.StageServiceRepository;

@Service
public class StageService implements StageServiceRepository<Stage> {

    @Autowired
    private StageDao stageDao;

    @Override
    public void save(Stage stage) {
        stageDao.save(stage);
    }

	public Optional<Stage> findById(Long stage_id) {
		return stageDao.findById(stage_id);
	}
	
	public long findFestival_Id (Long stage_id) {
		return stageDao.findFestivalId(stage_id);
	}

}
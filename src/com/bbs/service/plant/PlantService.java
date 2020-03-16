package com.bbs.service.plant;

import java.util.List;

import com.bbs.entity.Plant;

public interface PlantService {
	boolean savePlant(Plant plant);

	List<Plant> getPlateList();
}

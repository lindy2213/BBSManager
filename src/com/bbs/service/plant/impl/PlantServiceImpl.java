package com.bbs.service.plant.impl;

import java.util.List;

import com.bbs.dao.plant.PlantDao;
import com.bbs.dao.plant.impl.PlantDaoImpl;
import com.bbs.entity.Plant;
import com.bbs.service.plant.PlantService;

public class PlantServiceImpl implements PlantService {
	// 创建一个数据访问层的对象
	private PlantDao pd = new PlantDaoImpl();
	@Override
	public boolean savePlant(Plant plant) {
		int result = pd.savaPlant(plant);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Plant> getPlateList() {
		// TODO Auto-generated method stub
		return pd.getPlateList();
	}
}

package com.bbs.dao.plant;

import java.util.List;

import com.bbs.entity.Plant;

/**
 * 操作模块的接口
 * @author lindy
 * @创建时间 2020年3月16日下午2:53:07
 */
public interface PlantDao {
	int savaPlant(Plant plant);

	List<Plant> getPlateList();
}

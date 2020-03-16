package com.bbs.dao.plant.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.dao.plant.PlantDao;
import com.bbs.entity.Plant;

public class PlantDaoImpl implements PlantDao {
	private ResultSet rs;
	@Override
	public int savaPlant(Plant plant) {
		// 创建SQL命令
		String sql = "insert into bbs_plate(plateTitle,plateMessage,isEnable) values(?,?,0)";
		Object[] params = {plant.getPlantTitle(),plant.getPlantMessage()};
		return DataUtils.executeUpdate(sql, params);
	}

	@Override
	public List<Plant> getPlateList() {
		List<Plant> list = new ArrayList<Plant>();
		try {
			String sql = "select * from bbs_plate";
			rs = DataUtils.queryAll(sql, null);
			while (rs.next()) {
				list.add(new Plant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return list;
	}
	
}

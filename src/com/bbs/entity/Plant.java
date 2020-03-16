package com.bbs.entity;
/**
 * 模块类
 * @author lindy
 * @创建时间 2020年3月16日下午2:49:41
 */
public class Plant {
	private int plantId;// 模块编号
	private String plantTitle;// 模块标题
	private String plantMessage;// 模块信息
	private int isEnable;// 是否屏蔽
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantTitle() {
		return plantTitle;
	}
	public void setPlantTitle(String plantTitle) {
		this.plantTitle = plantTitle;
	}
	public String getPlantMessage() {
		return plantMessage;
	}
	public void setPlantMessage(String plantMessage) {
		this.plantMessage = plantMessage;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public Plant(String plantTitle, String plantMessage) {
		super();
		this.plantTitle = plantTitle;
		this.plantMessage = plantMessage;
	}
	public Plant(int plantId, String plantTitle, String plantMessage, int isEnable) {
		super();
		this.plantId = plantId;
		this.plantTitle = plantTitle;
		this.plantMessage = plantMessage;
		this.isEnable = isEnable;
	}
}

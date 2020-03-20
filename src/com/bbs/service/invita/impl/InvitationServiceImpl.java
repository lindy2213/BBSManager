package com.bbs.service.invita.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbs.dao.invita.InivtationAnsDao;
import com.bbs.dao.invita.InvitaTionDao;
import com.bbs.dao.invita.impl.InivtationAnsDaoImpl;
import com.bbs.dao.invita.impl.InvitationDaoImpl;
import com.bbs.entity.Invitation;
import com.bbs.entity.InvitationAns;
import com.bbs.service.invita.InvitationService;

public class InvitationServiceImpl implements InvitationService {
	private InvitaTionDao dao = new InvitationDaoImpl();
	private InivtationAnsDao adao = new InivtationAnsDaoImpl();
	@Override
	public boolean saveInvitation(Invitation invita) {
		int result = dao.saveInvitation(invita);
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Invitation> getInviList() {
		// TODO Auto-generated method stub
		return dao.getInviList();
	}
	@Override
	public Invitation findInvi(String inviId) {
		// TODO Auto-generated method stub
		return dao.findInvi(inviId);
	}
	@Override
	public boolean updateInvi(Invitation invi) {
		int result = dao.updateInvi(invi);
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public Map<String, Object> findDetials(String inviId) {
		Map<String, Object> ins = new HashMap<String, Object>();
		// 獲得執行id的帖子對象
		Invitation invi = dao.findInvi(inviId);
		// 獲得指定id首頁回復鑫的數據
		List<InvitationAns> ans = adao.findByInivid(inviId);
		// 將獲得的數據保存到map集合
		ins.put("invi", invi);
		ins.put("ans", ans);
		return ins;
	}
	@Override
	public boolean saveInviAns(InvitationAns ans) {
		int result = adao.saveInivAns(ans);
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}

}

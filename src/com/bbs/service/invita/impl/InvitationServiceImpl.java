package com.bbs.service.invita.impl;

import java.util.List;

import com.bbs.dao.invita.InvitaTionDao;
import com.bbs.dao.invita.impl.InvitationDaoImpl;
import com.bbs.entity.Invitation;
import com.bbs.service.invita.InvitationService;

public class InvitationServiceImpl implements InvitationService {
	private InvitaTionDao dao = new InvitationDaoImpl();
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

}

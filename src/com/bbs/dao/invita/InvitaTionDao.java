package com.bbs.dao.invita;

import java.util.List;

import com.bbs.entity.Invitation;

public interface InvitaTionDao {
	int saveInvitation(Invitation invita);

	List<Invitation> getInviList();
}

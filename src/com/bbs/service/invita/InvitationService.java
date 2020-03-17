package com.bbs.service.invita;

import java.util.List;

import com.bbs.entity.Invitation;

public interface InvitationService {
	boolean saveInvitation(Invitation invita);

	List<Invitation> getInviList();
}

package com.bbs.service.invita;

import java.util.List;
import java.util.Map;

import com.bbs.entity.Invitation;
import com.bbs.entity.InvitationAns;

public interface InvitationService {
	boolean saveInvitation(Invitation invita);

	List<Invitation> getInviList();

	Invitation findInvi(String inviId);

	boolean updateInvi(Invitation invi);

	Map<String, Object> findDetials(String inviId);

	boolean saveInviAns(InvitationAns ans);
}

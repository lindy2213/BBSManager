package com.bbs.dao.invita.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.commons.DataUtils;
import com.bbs.dao.invita.InvitaTionDao;
import com.bbs.entity.Invitation;

public class InvitationDaoImpl implements InvitaTionDao {
	private ResultSet rs;
	@Override
	public int saveInvitation(Invitation invita) {
		String sql = "INSERT INTO bbs_invitation(invitationId,invitationMessage,userId,plateId,categoryId,isPass,isEnable,isCream,invitationCreate)VALUES(?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		Object[] params = {invita.getInvitationId(),invita.getInvitationMessage(),invita.getUserId(),invita.getPlateId(),invita.getCategoryId()};
		return DataUtils.executeUpdate(sql, params);
	}

	@Override
	public List<Invitation> getInviList() {
		List<Invitation> list = new ArrayList<Invitation>();
		try {
			String sql = "SELECT i.*,p.plateTitle,c.category FROM bbs_invitation i INNER JOIN bbs_plate p ON(i.`plateId`=p.`plateId`) INNER JOIN bbs_category c ON(i.`categoryId`=c.`categoryId`)";
			rs = DataUtils.queryAll(sql, null);
			while (rs.next()) {
				Invitation invi = new Invitation(rs.getString("invitationId"), rs.getString("invitationMessage"), rs.getString("userId"), 
						rs.getString("plateTitle"), rs.getString("category"), rs.getInt("isPass"), rs.getInt("isEnable"), rs.getInt("isCream"),
						rs.getDate("invitationCreate"), rs.getDate("invitationModify"));
				list.add(invi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public Invitation findInvi(String inviId) {
		Invitation invi = null;
		try {
			String sql = "select invitationId,invitationMessage,userId,plateId,categoryId from bbs_invitation where invitationId=?";
			Object[] params = {inviId};
			rs = DataUtils.queryAll(sql, params);
			if(rs.next()) {
				invi = new Invitation(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataUtils.closeAll(null, null, rs);
		}
		return invi;
	}

	@Override
	public int updateInvi(Invitation invi) {
		String sql = "update bbs_invitation set invitationMessage=?,userId=?,plateId=?,categoryId=?,invitationModify=default where invitationId=?";
		Object[] params = {invi.getInvitationMessage(),invi.getUserId(),invi.getPlateId(),invi.getCategoryId(),invi.getInvitationId()};
		return DataUtils.executeUpdate(sql, params);
	}

}

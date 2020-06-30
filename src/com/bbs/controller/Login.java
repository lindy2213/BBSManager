package com.bbs.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.bbs.entity.Plant;
import com.bbs.entity.User;
import com.bbs.service.UserService;
import com.bbs.service.impl.UserServiceImpl;
import com.bbs.service.plant.PlantService;
import com.bbs.service.plant.impl.PlantServiceImpl;
@WebServlet("/Login")
public class Login extends HttpServlet {
	// 鍒涘缓涓氬姟灞傛帴鍙ｅ璞�
	private UserService us=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("测试pull的操作，实现组员之间的项目管理");
		//鑾峰緱椤甸潰鍙傛暟
		String userId=req.getParameter("userId");
		String userPsw=req.getParameter("userpsw");
		userPsw = DigestUtils.md5Hex(userPsw);
		//璋冪敤涓氬姟灞傞獙璇佺櫥褰曠殑鏂规硶证
		boolean isOk=us.Verification(userId, userPsw);
		//鍒ゆ柇缁撴灉锛屾牴鎹粨鏋滆繘琛岄〉闈㈣烦杞�
		if(isOk) {
			// 鑾峰緱鎵�鏈夌殑妯″潡淇℃伅
			PlantService ps = new PlantServiceImpl();
			List<Plant> plist = ps.getPlateList();
			req.getSession().setAttribute("plist", plist);
			
			req.getSession().setAttribute("userId", userId);
			req.getRequestDispatcher("UserServlet?op=index").forward(req, resp);
		}else {
			resp.sendRedirect("login.html");
		}
	}

}

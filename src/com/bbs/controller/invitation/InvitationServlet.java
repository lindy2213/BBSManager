package com.bbs.controller.invitation;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.entity.Invitation;
import com.bbs.entity.InvitationAns;
import com.bbs.service.invita.InvitationService;
import com.bbs.service.invita.impl.InvitationServiceImpl;

/**
 * Servlet implementation class Invitation
 */
@WebServlet("/InvitationServlet")
public class InvitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InvitationService invis = new InvitationServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if("add".equals(op)) {
			saveInvitation(request,response);
		}else if("show".equals(op)) {
			findAll(request,response);
		}else if("findById".equals(op)) {
			findyIniv(request,response);
		}else if("update".equals(op)) {
			updateInvi(request,response);
		}else if("details".equals(op)) {
			findDetails(request,response);
		}else if("addans".equals(op)) {
			saveInviAns(request,response);
		}
	}

	private void saveInviAns(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 得到系统的默认时间
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String userId = request.getParameter("userId");
		String inviId = request.getParameter("inviId");
		String ansMessage = request.getParameter("ansMessage");
		String ansId = userId+format.format(date);
		// 創建要報錯的回復對象
		InvitationAns ans = new InvitationAns(ansId, ansMessage, inviId, userId);
		boolean isOk = invis.saveInviAns(ans);
		if(isOk) {
			findDetails(request,response);
		}else {
			response.sendRedirect("server/single-no-sidebar.jsp");
		}
	}

	private void findDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 調用業務層中查詢帖子詳情的方法
		// 一個是帖子的信息，一個是該帖子所有的回復信息
		String inviId = request.getParameter("inviId");
		Map<String, Object> ins = invis.findDetials(inviId);
		request.getSession().setAttribute("ins", ins);
		response.sendRedirect("server/single-no-sidebar.jsp");
	}

	private void updateInvi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		String userId = request.getParameter("userId");
		String invitationId = request.getParameter("invitationId");
		String invitationMessage = request.getParameter("invitationMessage");
		int plantId = Integer.parseInt(request.getParameter("plantId"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Invitation invi = new Invitation(invitationId, invitationMessage, userId, plantId, categoryId);
		boolean isOk = invis.updateInvi(invi);
		if (isOk) {
			pw.write("{\"result\":\"true\"}");
		} else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
	}

	private void findyIniv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String inviId = request.getParameter("inviId");
		Invitation invi = invis.findInvi(inviId);
		if(invi != null) {
			request.getSession().setAttribute("invi", invi);
			response.sendRedirect("server/invitation-edit.jsp");
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Invitation> invList = invis.getInviList();
		request.getSession().setAttribute("invList", invList);
		response.sendRedirect("server/order-list.jsp");
	}

	private void saveInvitation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		String invitationMessage = request.getParameter("invitationMessage");
		String plantId = request.getParameter("plantId");
		String categoryId = request.getParameter("categoryId");
		System.out.println(invitationMessage+","+plantId+","+categoryId);
		String userId = request.getParameter("userId");
		// 得到系统的默认时间
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String invitationId = userId+format.format(date);
		
		Invitation invi = new Invitation(invitationId, invitationMessage, userId, Integer.parseInt(plantId), Integer.parseInt(categoryId));
		boolean isOk = invis.saveInvitation(invi);
		if (isOk) {
			pw.write("{\"result\":\"true\"}");
		} else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc") //★servlet 도메인 주소 별명
public class PhonebookController extends HttpServlet {

	// 디폴트 생성자 지움
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// System.out.println("PhonebookController");
		//파라미터에 따라서 호출 action(호출 이름은 정하는것 action이 아니어도 된다.)
		String act = request.getParameter("action");
		//System.out.println(act);
		
		if("list".equals(act)) {
			//테스트
			System.out.println("action=list");
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			System.out.println(personList); //주소 넣으면 프린트는 투스트링 찍어줌
			
			//html과 list를 섞어서 표현해야한다.
			//servlet 으로는 표현이 복잡하다 --> jsp를 이용한다.(jsp를 생성해서 작성)
			//1)데이터 넣어주기
			request.setAttribute("pList",personList); //괄호("별명key값", 데이터)
		
			//2-1)포워드(포워드할 경로 적어줌)-> 컨트롤러가 jsp에 일을시킴
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			//2-2)request(데이터가 담겨있음),response를 보내주겠다
			rd.forward(request, response);
			
		} else if("writeForm".equals(act)) {
			
			System.out.println("action=writeForm");
			
			//데이터 넣어줄 것 없음
			
			//포워드
			//1-1) insert.jsp에 일시킴
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			//1-2) 1-1)경로에 request,response 보냄
			rd.forward(request, response);


		} else if("write".equals(act)) {
			System.out.println("action=writeForm");
			
			//파라미터 호출 
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 객체화 
			PersonVo personVo = new PersonVo(name,hp,company);
			//System.out.println(personVo);

			//dao 메모리에 올리기
			PhoneDao phoneDao = new PhoneDao();
			//저장하기 dao.inert();
			phoneDao.personInsert(personVo);
			
			//등록된 리스트 뿌려주기 -> 리다이렉트 (포워드X)
			//사용자에게 들어갈 주소를 넣어주는것 파일 경로 아님!! 
			response.sendRedirect("/phonebook2/pbc?action=list");
		
		}else if("delete".equals(act)) {
			System.out.println("action=delete");
			
			//파라미터로 호출
			String paraid = request.getParameter("id");
			int id = Integer.parseInt(paraid);
			
			//dao 메모리에 올리기
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(id);
			
			//리다이렉트 
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if ("updateForm".equals(act)) {
			
			System.out.println("action=updateForm");
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(id);
			//1)데이터 넣어주기
			request.setAttribute("personVo",personVo); //괄호("별명key값", 데이터)
			//포워드
			//2-1) insert.jsp에 일시킴
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			//2-2) 1-1)경로에 request,response 보냄
			rd.forward(request, response);
			
			
		} else if("update".equals(act)){
			
			System.out.println("action=update");
			
			//personId일치-> db , 수정
			//파라미터 호출
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			String paraId = request.getParameter("id");
			int id = Integer.parseInt(paraId);
			
			//dao 올리기
			PhoneDao phoneDao = new PhoneDao();
			
			//vo 객체화
			PersonVo personVo = new PersonVo(name,hp,company,id);
			
			//수정
			phoneDao.personUpdate(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else {
			System.out.println("파라미터 없음");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}

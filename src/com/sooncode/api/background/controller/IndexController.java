package com.sooncode.api.background.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

 
import com.sooncode.api.background.entity.Company;
import com.sooncode.api.background.entity.Control;
import com.sooncode.api.background.entity.Project;
import com.sooncode.api.background.entity.Role;
import com.sooncode.api.background.entity.User;
 
import com.sooncode.api.background.util.Md5;
import com.sooncode.soonjdbc.page.Many2Many;
import com.sooncode.soonjdbc.page.One2Many;
import com.sooncode.soonjdbc.page.One2One;
import com.sooncode.soonjdbc.service.JdbcService;

@Controller
@RequestMapping("/indexController")
public class IndexController {

	
	@Autowired
	private JdbcService jdbcService;

	private static Logger logger = Logger.getLogger("IndexController.class");

	/**
	 * 登录系统
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpSession session) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		 
		User newUser = jdbcService.get(user); 
		Map<String, Object> map = new HashMap<String, Object>();

		if (newUser != null && newUser.getPassword().equals(Md5.GetMD5Code(password))) {
		 

			session.setAttribute("user", newUser);
			map.put("userId", newUser.getUserId());

			Company company = new Company();

			company.setCompanyId(newUser.getCompanyId());

			company = jdbcService.get(company);

			List<One2One> users = jdbcService.getPage(1L, 1L, newUser, new Role(), new Company()).getOne2One(); 
				
			 																				 
			if (users != null && users.size() == 1) {
				User thisUser = users.get(0).getOne(User.class);
				Role thisRole = users.get(0).getOne(Role.class);
				Control c =  new Control();
				c.setUserId(thisUser.getUserId());
			    List<Control> controls  =  jdbcService.gets(c);	
			    updateSession(session,"controls", controls);
			    updateSession(session,"user", thisUser);
			    updateSession(session,"company", company);
			    updateSession(session,"role", thisRole);
			    map.put("page", "introduction");
			    return new ModelAndView("introduction/introduction", map);
			}


		}
		return new ModelAndView("login/login", map);

	}

	private void updateSession(HttpSession session,String key,Object value){
		    session.removeAttribute(key);
		    session.setAttribute(key, value);
	}
	
	
	
	@RequestMapping("/edIndex")
	public ModelAndView edIndex(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		Project p = new Project();
		p.setProjectId("9B8AACB65185483DAADF914C26703FD9");

		return new ModelAndView("ed_index", map);
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		Project p = new Project();
		p.setProjectId("9B8AACB65185483DAADF914C26703FD9");

		logger.info(p);
		return new ModelAndView("index", map);
	}

	/**
	 * 挑转至注册页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		return new ModelAndView("register/register", map);
	}

	/**
	 * 挑转至自定义调试页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "test");
		return new ModelAndView("test/test", map);
	}

	/**
	 * 挑转至自定义入门页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/introduction")
	public ModelAndView introduction(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", "introduction");
		return new ModelAndView("introduction/introduction", map);
	}

	/**
	 * 挑转至项目管理页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/project")
	public ModelAndView project(HttpServletRequest request, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("role");
		// 管理员

		if (role.getRoleCode().equals("ADMIN")) { // 管理员
			Company company = new Company();
			company.setCompanyId(user.getCompanyId());
			One2Many<Company, Project> companyAndProject = jdbcService.getPage(1L, 10L, company, new Project()).getOne2Many();
			company = companyAndProject.getOne();
			
			List<Project> projects;
			if (company == null) {
				projects = null;
			} else {
				projects = companyAndProject.getMany();

			}
			map.put("projects", projects);
		} else if (role.getRoleCode().equals("GENERAL")) {// 普通用户
			Control control = new Control();
			control.setUserId(user.getUserId());
			Many2Many<User, Control, Project> ucp = jdbcService.getPage(1L, 10L, user, new Control(), new Project()).getMany2Many();
			 

			List<Project> projects = new ArrayList<>();
			if (user != null) {
				projects = ucp.getMany(Project.class);
			}
			map.put("projects", projects);

		}
		map.put("page", "project");
		return new ModelAndView("project/project_list", map);
	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit")
	public ModelAndView exit(HttpServletRequest request, HttpSession session) {

		session.removeAttribute("user");

		Map<String, Object> map = new HashMap<String, Object>();

		return new ModelAndView("login/login", map);
	}

	@RequestMapping("/error404")
	public ModelAndView error404(HttpServletRequest request,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "error404");
		return new ModelAndView("error/404", map);
	}
	@RequestMapping("/error500")
	public ModelAndView error500(HttpServletRequest request,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "error500");
		return new ModelAndView("error/500", map);
	}
}

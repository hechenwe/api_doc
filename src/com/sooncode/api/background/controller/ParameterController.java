package com.sooncode.api.background.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooncode.api.background.entity.Parameter;
import com.sooncode.api.background.service.ParameterService;

 
 
 
 

@Controller
@RequestMapping("/parameter")
public class ParameterController {
	@Autowired
	private ParameterService parameterService;

	 
	@RequestMapping("/saveParameter")
	 
	public ModelAndView saveParameter(HttpServletRequest request, HttpSession session) {

		String interfacId = request.getParameter("interfacId");
		String parameterId = request.getParameter("parameterId");
		String parameterName = request.getParameter("parameterName");
		String parameterCode = request.getParameter("parameterCode");
		String parameterDataType = request.getParameter("parameterDataType");
		String minLength = request.getParameter("minLength");
		String maxLength = request.getParameter("maxLength");
		String parameterExample = request.getParameter("parameterExample");
		String parameterExplain = request.getParameter("parameterExplain");
		String isMust = request.getParameter("isMust");
		String type = request.getParameter("type");

		Parameter p = new Parameter();
		p.setParameterName(parameterName);
		p.setParameterDataType(parameterDataType);
		p.setCreatDate(new Date());
		p.setParameterExample(parameterExample);
		p.setParameterExplain(parameterExplain);
		p.setParameterCode(parameterCode);
		if(maxLength!=null && !maxLength.equals("")){
			p.setMaxLength(Integer.parseInt(maxLength));
		}else{
			p.setMaxLength(Integer.MAX_VALUE);
		}
		if(minLength!=null && !minLength.equals("")){
		 
			p.setMinLength(Integer.parseInt(minLength));
		}else{
			p.setMinLength(1);
		}
		p.setIsMust(isMust);
		Integer weight = parameterService.parameterDao.getMaxWeight(interfacId);
		if (weight == null) {
			weight = 1;
		} else {
			weight = weight + 1;
		}
		p.setWeight(weight);
		 
		if(type != null && type.equals("UPDATE")){
			 p.setParameterId(parameterId);
			 parameterService.parameterDao.update(p);
			 Parameter newP = new Parameter();
			 newP.setParameterId(parameterId);
			 p = parameterService.parameterDao.get(newP);
		}else{
			
			p.setParameterId(parameterId.replace("-", ""));
			p.setInterfacId(interfacId.replace("-", ""));
			 parameterService.parameterDao.save(p);
		}
		  Map<String,Object> map = new HashMap<>();
		  map.put("parameter",p);
		  
		return new ModelAndView("parameter/table_tr_parameter",map);

	}

	@RequestMapping("/deleteParameter")
	@ResponseBody
	public String deleteParameter(HttpServletRequest request, HttpSession session) {
		String parameterId = request.getParameter("parameterId");
		Parameter p = new Parameter();
		p.setParameterId(parameterId.replace("-", ""));
		int n = parameterService.parameterDao.delete(p);
		if (n == 1) {
			return "1";
		} else {
			return "0";
		}
	}

	@RequestMapping("/updateParameter")

	public ModelAndView updateParameter(HttpServletRequest request) {
		String parameterId = request.getParameter("parameterId");
		Parameter p = new Parameter();
		p.setParameterId(parameterId.replace("-", ""));
		p = parameterService.parameterDao.get(p);
		Map<String, Object> map = new HashMap<>();
		map.put("parameter", p);
		return new ModelAndView("parameter/up_parameter", map);

	}
}

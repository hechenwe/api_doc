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

import com.sooncode.api.background.entity.ParameterRetur;
import com.sooncode.api.background.service.ParameterReturService;


/**
 * 返回值示例
 * 
 * @author hechen
 *
 */
@Controller()
@RequestMapping("/parameterRetur")
public class ParameterReturController {

	@Autowired
	private ParameterReturService parameterReturService;
    /**
     * 保存返回值参数
     * @param request
     * @return
     */
	@RequestMapping("/saveParameterRetur")
	public ModelAndView saveParameterRetur(HttpServletRequest request) {
	 
		String exampleId = request.getParameter("exampleId").trim().replace("-", "");
		String parameterId = request.getParameter("parameterId").trim().replace("-", "");
		String parameterName = request.getParameter("parameterName");
		String parameterCode = request.getParameter("parameterCode");
		String parameterDataType = request.getParameter("parameterDataType");
		String minLength = request.getParameter("minLength");
		String maxLength = request.getParameter("maxLength");
		String parameterExample = request.getParameter("parameterExample");
		String parameterExplain = request.getParameter("parameterExplain");
		String isMust = request.getParameter("isMust");

		ParameterRetur pr = new ParameterRetur();
		pr.setParameterId(parameterId);
		pr.setExampleId(exampleId);
		pr.setParameterName(parameterName);
		pr.setParameterCode(parameterCode);
		pr.setParameterDataType(parameterDataType);
		if(parameterDataType.equals("String")){
			pr.setMinLength(Integer.parseInt(minLength));
			pr.setMaxLength(Integer.parseInt(maxLength));
		}
		pr.setParameterExample(parameterExample);
		pr.setParameterExplain(parameterExplain);
		pr.setIsMust(isMust);
		pr.setWeight(1);
		pr.setCreatDate(new Date());
		 

		parameterReturService.parameterReturDao.saveOrUpdate(pr);
		Map<String,Object> map = new HashMap<>();
		map.put("p", pr);
		return new ModelAndView("parameter_return/table_tr_return_parameter",map);
	}
	
	/**
	 * 加载修改DIV数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateParameterRetur")
	public ModelAndView updateParameterRetur(HttpServletRequest request) {
		String parameterId = request.getParameter("parameterId");
		ParameterRetur pr = new ParameterRetur();
		pr.setParameterId(parameterId);
		
		pr = parameterReturService.parameterReturDao.get(pr);
		Map<String,Object> map = new HashMap<>();
		map.put("pr", pr);
		return new ModelAndView("parameter_return/add_up_parameter_return",map);
	}
	/**
	 * 加载添加DIV数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddParameterRueturn")
	public ModelAndView toAddParameterRueturn(HttpServletRequest request) {
		
		String exampleId = request.getParameter("exampleId");
		ParameterRetur pr = new ParameterRetur();
		pr.setExampleId(exampleId);
		 
		Map<String,Object> map = new HashMap<>();
		map.put("pr", pr);
		return new ModelAndView("parameter_return/add_up_parameter_return",map);
	}
	
	/**
	 * 删除 返回值 参数
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteParameterRetur")
	@ResponseBody
	public String deleteParameterRetur(HttpServletRequest request) {
		String parameterId = request.getParameter("parameterId");
		ParameterRetur pr = new ParameterRetur();
		pr.setParameterId(parameterId);
		Integer n = parameterReturService.parameterReturDao.delete(pr);
		
		if(n == 1){
			return "1";
		}else{
			return "0";
		}
		
	}
	
}

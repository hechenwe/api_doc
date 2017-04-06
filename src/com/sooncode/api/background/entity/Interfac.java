package com.sooncode.api.background.entity;
 
import java.util.List;


/**
 *   接口 
 * 
 *  @author HeChen 
 *
 */
 
public class Interfac implements java.io.Serializable{

    /** 序列化  */
    private static final long serialVersionUID = 1L; 
    
   
    /**接口编号*/
    private String interfacId; 
    
    
    /**模块编号*/
    private String moduleId; 
    
    
    /**接口名称*/
    private String interfacName; 
    
    
    /**接口代码*/
    private String interfacCode; 
    
    
    /**接口简介*/
    private String interfacIntro; 
    
    
    /**接口链接*/
    private String url; 
    
    
    /**返回数据类型*/
    private String returnDataType; 
    
    
    /**请求方式*/
    private String requestType; 
    
    
    /**风格*/
    private String style; 
    
    
    /**创建时间*/
    private java.util.Date creatDate; 
    
    
    /**权重*/
    private Integer weight; 
    
    
    /**参数格式*/
    private String parameterFormat; 
    
    /**JSON格式的参数*/
    private String jsonParameters;// JSON_PARAMETERS;
    
    
    
    /**模块*/
	private Module module ;
	
	 
 
 
    /** 参数_参数 */
	private  List<Parameter> parameters ;
	
		 
    /** 返回值_事例 */
	 private  List<Example> examples ; 
	
		 
 
 
  
                                                               
   
    //------------------------get,set 方法----------------------------
 
 
    /**接口编号*/ 
    public String getInterfacId(){  
      return interfacId;  
    }  
     /**接口编号*/
    public void setInterfacId(String interfacId){  
      this.interfacId = interfacId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**模块编号*/ 
    public String getModuleId(){  
      return moduleId;  
    }  
     /**模块编号*/
    public void setModuleId(String moduleId){  
      this.moduleId = moduleId;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**接口名称*/ 
    public String getInterfacName(){  
      return interfacName;  
    }  
     /**接口名称*/
    public void setInterfacName(String interfacName){  
      this.interfacName = interfacName;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**接口代码*/ 
    public String getInterfacCode(){  
      return interfacCode;  
    }  
     /**接口代码*/
    public void setInterfacCode(String interfacCode){  
      this.interfacCode = interfacCode;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**接口简介*/ 
    public String getInterfacIntro(){  
      return interfacIntro;  
    }  
     /**接口简介*/
    public void setInterfacIntro(String interfacIntro){  
      this.interfacIntro = interfacIntro;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**接口链接*/ 
    public String getUrl(){  
      return url;  
    }  
     /**接口链接*/
    public void setUrl(String url){  
      this.url = url;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**返回数据类型*/ 
    public String getReturnDataType(){  
      return returnDataType;  
    }  
     /**返回数据类型*/
    public void setReturnDataType(String returnDataType){  
      this.returnDataType = returnDataType;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**请求方式*/ 
    public String getRequestType(){  
      return requestType;  
    }  
     /**请求方式*/
    public void setRequestType(String requestType){  
      this.requestType = requestType;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**风格*/ 
    public String getStyle(){  
      return style;  
    }  
     /**风格*/
    public void setStyle(String style){  
      this.style = style;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**创建时间*/ 
    public java.util.Date getCreatDate(){  
      return creatDate;  
    }  
     /**创建时间*/
    public void setCreatDate(java.util.Date creatDate){  
      this.creatDate = creatDate;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**权重*/ 
    public Integer getWeight(){  
      return weight;  
    }  
     /**权重*/
    public void setWeight(Integer weight){  
      this.weight = weight;  
    } 
    
    
   //----------------------------------------------------------------
   
   
    /**参数格式*/ 
    public String getParameterFormat(){  
      return parameterFormat;  
    }  
     /**参数格式*/
    public void setParameterFormat(String parameterFormat){  
      this.parameterFormat = parameterFormat;  
    } 
    
    
   //----------------------------------------------------------------
   
   
  
    /**模块*/ 
    public Module getModule(){  
      return this.module;  
    }  
    /**模块*/
    public void setModule(Module module){  
      this.module = module;  
    } 
    
    
    //----------------------------------------------------------------
  
  
 
 
   
 
 
	 
	
	/**参数_参数*/ 
    public List<Parameter> getParameters(){  
      return this.parameters;  
    }  
    /**参数_参数*/
    public void setParameters(List<Parameter> parameters){  
      this.parameters = parameters;  
    } 
    
    
    //----------------------------------------------------------------
    
    	 	 
	 
	
	/**返回值_事例*/  
    public List<Example> getExamples(){  
      return this.examples;  
    }  
    /**返回值_事例*/ 
    public void setExamples(List<Example> examples){  
      this.examples = examples;  
    }  
    
    
    
    
    //----------------------------------------------------------------
    
    	 	 
 


     public String getJsonParameters() {
		return jsonParameters;
	}
	 
	public void setJsonParameters(String jsonParameters) {
		this.jsonParameters = jsonParameters;
	}
	//----------------------------------------------------------------
	@Override
	public String toString() {
		return "{\"interfacId\":\"" + interfacId + "\",\"moduleId\":\"" + moduleId + "\",\"interfacName\":\"" + interfacName + "\",\"interfacCode\":\"" + interfacCode + "\",\"interfacIntro\":\"" + interfacIntro + "\",\"url\":\"" + url + "\",\"returnDataType\":\"" + returnDataType + "\",\"requestType\":\"" + requestType + "\",\"style\":\"" + style + "\",\"creatDate\":\"" + creatDate + "\",\"weight\":\"" + weight + "\",\"parameterFormat\":\"" + parameterFormat + "\",\"jsonParameters\":\"" + jsonParameters + "\",\"module\":\"" + module + "\",\"parameters\":\"" + parameters + "\",\"examples\":\"" + examples + "\"}  ";
	}
     

}

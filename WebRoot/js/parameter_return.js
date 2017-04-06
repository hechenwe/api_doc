//-------------------------------------显示添加返回参数DIV-------------------------------------------
function showAddParameterReturn(){
	 
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var exampleId = dataKeys[1]; 
	 var url = "../parameterRetur/toAddParameterRueturn.html?exampleId="+exampleId;
	 $.get(url, function(data) {
		 $("#"+exampleId+"-div").removeClass("div-display");
		 $("#"+exampleId+"-div").append(data);
	})	
}

function toUpdateParameterReturn(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var exampleId = dataKeys[0]; 
	 var parameterId = dataKeys[1]; 
	 var url = "../parameterRetur/updateParameterRetur.html?parameterId="+parameterId;
	 $.get(url, function(data) {
		 $("#"+exampleId).append(data);
		})
}

//-----------------------------------------------------保存-返回参数----------------------------------------------
function saveEaxmpleRetur(exampleId) {
	var parameterId = $("#"+exampleId+"parameterId").val();
	if(parameterId==""){
		parameterId = Math.uuid();
	}
	var parameterName = $("#"+ exampleId +"parameterName").val();
	var parameterCode = $("#"+ exampleId +"parameterCode").val();
	var parameterDataType = $("#"+ exampleId +"parameterDataType").val();
	var minLength = $("#"+ exampleId +"minLength").val();
	var maxLength = $("#"+ exampleId +"maxLength").val();
	var parameterExample = $("#"+ exampleId +"_parameterExample").val();//parameterExample
	var parameterExplain = $("#"+ exampleId +"_parameterExample").val();
	var isMust = $('input[name="'+ exampleId +'isMust"]:checked').val();
	//var isMust = $('input[name="isMust"]:checked').val();
	var weight = $("#"+ exampleId +"weight").val();
	 
	var urlString ="../parameterRetur/saveParameterRetur.html"+
	               "?exampleId="+exampleId+
	               "&parameterId="+parameterId+
	               "&parameterName="+parameterName+
	               "&parameterCode="+parameterCode+
	               "&parameterDataType="+parameterDataType+
	               "&minLength="+minLength+
	               "&maxLength="+maxLength+
	               "&parameterExample="+parameterExample+
	               "&parameterExplain="+parameterExplain+
	               "&isMust="+isMust+
	               "&weight="+weight;
	
	$.get(urlString, function(data) {
	     $("#"+parameterId).remove();
		 $("#parameter_return"+exampleId).append(data);
		 parameterReturnMousedown();
	})	
}  

function deleteParameterReturn(){
	 
	
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var parameterId = dataKeys[1]; 
	if (confirm("确认要删除？")) {
		var url ="../parameterRetur/deleteParameterRetur.html?parameterId="+parameterId;

		$.get(url, function(data) {

			if (data == "1") {
				$("#"+parameterId).remove();
			}
		})
	}
}



// -----------------------------添加参数----------------------------------------
function toAddParameter() {
	
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	
	var url = '../interface/getInterfaceParameterFormatParameterSize.html?interfacId=' + interfacId;

	$.get(url, function(data) {
		var parameterFormat = data.parameterFormat;
		var parameterSize = data.parameterSize;

//		if (parameterFormat == "JSON") {
//			if (parameterSize == 0) {
//				 
//				$("#div_parameter"+interfacId).toggleClass('div-display');
//			} else {
//				alert("JSON格式的参数已添加!");
//			}
//		} else {
			 
			$("#div_parameter"+interfacId).toggleClass('div-display');
		//}
		$("#interfacId").val(interfacId);
	})

}
function updateParameter(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0];
	 var parameterId = dataKeys[1];
	 var url = '../parameter/updateParameter.html?parameterId='+parameterId;
	$.get(url,function (data){
		//$("#up-parameter"+interfacId).empty();
		$("#up-parameter"+interfacId).append(data);//.toggleClass('div-display');;
		 
	})
}

//-----------------------------------------------------保存参数约束说明----------------------------------------------
function deleteParameter() {
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var parameterId = dataKeys[1];
	if (confirm("确认要删除？")) {
	var urlString = "../parameter/deleteParameter.html?parameterId="+parameterId;
	 
    $.get(urlString, function(data) {
		
		if(data=="1"){
			//alert(parameterId);
			$("#tr-"+parameterId).remove();
		}else{
			alert("删除失败!");
		}
	})
	
	}	  
}


//-----------------------------------------------------保存保存参数----------------------------------------------
function saveParameter() {
	var projectId = $("#input_projectId").val();
	var interfacId = $("#interfacId").val();
 
	var parameterId = Math.uuid();//.replace(/-/gm,'');
	var parameterName = $("#parameterName").val();
	var parameterCode = $("#parameterCode").val();
	var minLength = $("#minLength").val();
	var maxLength = $("#maxLength").val();
	var parameterExample = $("#parameterExample").val();
	var parameterExplain = $("#parameterExplain").val();
	var weight = $("#weight").val();
	var parameterDataType = $("#parameterDataType").val();
	var isMust = $('input[name="isMust"]:checked').val();

	var urlString = "../parameter/saveParameter.html?projectId=" + projectId;
	urlString = urlString + "&interfacId=" + interfacId + "&parameterId=" + parameterId + "&parameterName=" + parameterName + "&parameterCode=" + parameterCode + "&minLength=" + minLength;
	urlString = urlString + "&maxLength=" + maxLength + "&parameterExample=" + parameterExample + "&parameterExplain=" + parameterExplain + "&weight=" + weight;
	urlString = urlString + "&parameterDataType=" + parameterDataType + "&isMust=" + isMust;
	//alert(urlString);

	$.get(urlString, function(data) {

			$("#tbody-" + interfacId).append(data);
			//alert("保存参数成功!");
			var forms = $("#form");
			for (var i = 0; i < forms.length; i++) {
				forms[i].reset();

			}
		 

	})
}

function saveJsonParameter() {
    var dataKeys = $("#context-menu").attr("data-key").split(":");
	var interfacId = dataKeys[0];
    var jsonParameters =$("#textarea-"+interfacId).val();

	var urlString = "../interface/saveJsonParameters.html?interfacId=" + interfacId +"&jsonParameters="+jsonParameters;
	 
	$.get(urlString, function(data) {
		 

	})
}
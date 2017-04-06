// -----------------------------添加接口----------------------------------------
function toAddInterface() {
	 
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var moduleId = dataKeys[0];
	 var bool = $("#module"+moduleId).hasClass('div-display');
	 
	 if(bool){ //没有展开模块，时展开模块。
		 var imgId = "img_module_"+moduleId;
		 var string = $("#module"+moduleId).html().trim();
			if(string==""){
				$.get("../interface/getInterfaces.html?moduleId="+moduleId,function (data){
					$("#module"+moduleId).append(data);
					$("#"+imgId).attr("src","../images/down.png");
					$("#module"+moduleId).removeClass('div-display');
					
					var urlString = "../interface/toAddInterface.html?moduleId="+moduleId; //加载添加接口的div
					 $.get(urlString,function (data){
						 
						 $("#div_interfac"+moduleId).empty();
						 $("#div_interfac"+moduleId).append(data);
						 $("#div_interfac"+moduleId).removeClass('div-display');
					 })
				})
			}
		 
	 }else{
		 var urlString = "../interface/toAddInterface.html?moduleId="+moduleId;
		 $.get(urlString,function (data){
			 
			 $("#div_interfac"+moduleId).empty();
			 $("#div_interfac"+moduleId).append(data);
			 $("#div_interfac"+moduleId).removeClass('div-display');
		 })
		 
	 }
	                          
}


//----------------------------更新接口信息-----------------------------------------------------
function editInterface() {
	var interfacId = $("#context-menu").attr("data-key").split(":")[0];
	var urlString = '../interface/toEditInterface.html?interfacId=' + interfacId;
	
	$.get(urlString, function(data) {
		 
		 var moduleId =  $(data).find("#moduleId").val();
		 $("#div_interfac"+moduleId).empty();
		 $("#div_interfac"+moduleId).append(data);
		 $("#div_interfac"+moduleId).removeClass('div-display');
	})

}

//取消  保存接口 

function cancelSaveInterface(){
	  
	 var moduleId = $("#moduleId").val();
	 $("#div-intface-"+moduleId).removeClass('div-display');//显示 模块下的所用接口
	 $("#div_interfac"+moduleId).addClass('div-display');
	 
 } 


//-----------------------------------------------------保存接口----------------------------------------------
function saveInterfac() {
	 
	var moduleId = $("#moduleId").val();
	var interfacId = $("#add_up_interfacId").val()
	 
	var type = "ADD"
	if(interfacId==""){
		interfacId = Math.uuid();
	}else{
		type = "UPDATE"
	}
	var interfacName = $("#interfacName").val();
	var interfacCode = $("#interfacCode").val();
	var interfacIntro = $("#interfacIntro").val();
	var url = $("#url").val();
	var returnDataType = $('input[name="returnDataType"]:checked').val();
	var requestType = $('input[name="requestType"]:checked').val();
	var parameterFormat = $('input[name="parameterFormat"]:checked').val();
	var style = $('input[name="style"]:checked').val();

	var weight = $("#weight").val();
	var urlString = "../interface/saveInterface.html?";
	urlString = urlString + "moduleId=" + moduleId + "&interfacId=" + interfacId + "&interfacName=" + interfacName + "&interfacCode=" + interfacCode + "&interfacIntro=" + interfacIntro;
	urlString = urlString + "&url=" + url + "&returnDataType=" + returnDataType + "&requestType=" + requestType + "&parameterFormat=" + parameterFormat + "&weight=" + weight +"&style="+style;
	urlString = urlString +"&type="+type;
	$.get(urlString,function (data){
		$("#div_interfac"+moduleId).empty();//清理 添加和修改 接口div片段
		$("#div-intface-"+moduleId).removeClass('div-display');//显示 模块下的所用接口
		
		if(type=="UPDATE"){
			$("#div-interfac-"+interfacId).remove();
		}
		$("#div-intface-"+moduleId).append(data);//添加新的接口
		interfaceMousedown();//刷新右键菜单
		//window.location.reload();
	})
	 
}


 



//------------------删除接口------------------------
function deleteInterface() {
	var interfacId = $("#context-menu").attr("data-key").split(":")[0];
	if (confirm("确认要删除？")) {
		var url = '../interface/deleteInterface.html?interfacId=' + interfacId+"&projectId="+projectId;
		$.get(url,function (data){
			if(data == "1"){
				$("#h5-"+interfacId).remove();
				$("#hr-"+interfacId).remove();
			}
		})
		//window.location.href='../interface/deleteInterface.html?interfacId=' + interfacId+"&projectId="+projectId;
	} 
}

//--------------------移动接口----------------------
function upOrdownInterface(type, interfacId , weight ) {
	var url = '../interface/upOrdownInterface.html?projectId='+projectId+'&interfacId=' + interfacId+'&type='+type +'&weight='+weight;
	
	$.get(url,function (data){
		window.location.reload();
	})
}

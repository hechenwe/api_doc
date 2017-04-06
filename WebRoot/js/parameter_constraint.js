//-------------------------------------显示添加参数约束-------------------------------------------
function addParameterConstraint(){
	 var dataKeys = $("#context-menu").attr("data-key").split(":");
	 var interfacId = dataKeys[0]; 
	 var parameterId = dataKeys[1]; 
	
	$("#div_parameter_constraint_"+interfacId).toggleClass('div-display');
	 
	$("#"+interfacId+"_parameterId").val(parameterId);
	 
}



//-------------------------------------------------------------------------------------------------
function updateExample(exampleId,interfacId){
	 
	var exampleType = $("#exampleType-"+exampleId).val();  
	var exampleContent = $("#exampleContent-"+exampleId).val(); 
	var url = "../example/updateExample.html?exampleId="+exampleId + "&exampleType="+exampleType+"&exampleContent="+exampleContent;
	 
	$.get(url,function (data){
		
		if(data == "1"){
	     
		$("#div-exam-"+interfacId).empty();
		$("#small-example-"+exampleId).text(exampleContent);//	exampleContent-${example.exampleId}
		$("#example"+exampleId).removeClass('div-display')
		}
		
	})
	
	
	
}
//-----------------------------------------------------保存项目----------------------------------------------
		function saveProject() {

			var projectId = Math.uuid();
			 
			var projectName = $("#projectName").val();
			var projectCode = $("#projectCode").val();
			var projectIntro = $("#projectIntro").val();
			var weight = $("#weight").val();
			$.get("../project/saveProject.html?projectId=" + projectId + "&projectName=" + projectName + "&projectCode=" + projectCode + "&projectIntro=" + projectIntro + "&weight=" + weight, function(data) {
				$("#page-wrapper").empty();

				if (data == "1") {
					var html = '<ul  class="ul-root" id="'+ projectId +'">' + '<li><span onclick="openOrClose("module",' + projectId + ');"></span><a ondblclick="openOrClose("module",' + projectId + ');" >项目名称：' + projectName + '</a> <img src="../images/add-M2.png" onclick="toAddModule(\''+projectId+'\');"></img>' + '</ul>';
					$("#div_tree").append(html);
				}
			})

		}
		 
		
		
		
		
		//-----------------------------------------------------保存-返回参数说明----------------------------------------------
		  function saveValueExplain(exampleId) {
		      
			var parameterId = $("#"+ exampleId +"valueExplainParameterId").val();
			var valueExplainId = Math.uuid();
			var value = $("#"+ exampleId +"value").val();
			var valueMeaning = $("#"+ exampleId +"valueMeaning").val();
			var weight = $("#"+ exampleId +"valueExplainWeight").val();
		    
			var urlString ="../valueExplain/savaValueExplain.html"+
			               "?parameterId="+parameterId+
			               "&valueExplainId="+valueExplainId+
			               "&value="+value+
			               "&valueMeaning="+valueMeaning+
			               "&weight="+weight;
			//alert(urlString);
			$.get(urlString, function(data) {
				 
				if (data == "1") {
					alert("添加成功")
				}else{
					alert("添加失败")
				}
			})	
		} 
		  
		  //------------------------------------get请求------------------------------------------------
			function httpGet(urlString,id,parentId,nodeName,openString ,addString ,clickName){
				$.get(urlString, function(data) {
					$("#page-wrapper").empty();
					if (data == "1") {
						var html = '<ul  class="ul-root" id="'+ id +'">' + '<li><span onclick="openOrClose(\''+ openString +'\',\'' + id + '\');"></span><a ondblclick="openOrClose(\''+ openString +'\',\'' + id + '\');" >' + nodeName + '</a><img src="../images/'+addString+'" onclick="'+clickName+'(\'' + id + '\');"></img>' + '</ul>';
						if (data == "1") {
							$("#" + parentId).append(html);
							openOrClose( openString, parentId);
						}
					}
				})	
			}
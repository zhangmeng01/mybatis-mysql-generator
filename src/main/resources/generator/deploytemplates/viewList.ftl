${contextPath}
<!DOCTYPE html>
<html lang="en">
<head> 
${common}
</head>
<body>
<form autocomplete="off" action="" method="post" id="${className}Form">
<button type="button" class="btn_green_big" onclick="add${className}()">添加</button>
<button type="button" class="btn_green_big" onclick="edit${className}()">编辑</button>
<button type="button" class="btn_green_big" onclick="delete${className}()">删除</button>
<p>&nbsp;</p>  
    <div class="table-responsive">
    <div style="min-height:441px;background:#ECF0F1; width:98%; overflow-x:auto;">
       <table id="${className}Table" class="table graytext operat_a" style="width:100%;"> 
         <thead>
	        <tr class="green h40">
	          <th><input type="checkbox"  checkbox-all="checkboxItem"  style="background:none"></th>
	           <#list columns as column>
	            <th>${column.columnComment}</th> 
               </#list>  
	        </tr>
	       </thead>
	       <tbody class="checkboxItem">
           </tbody>     
      </table>
      </div>
    <div id="page" style=" margin-top:20px;"></div>    
  </div>  
</form> 

 <script id="${className}TableTmpl" type="text/html">    
     {{each list as info i}}  
           <tr class="gray" id="tr_{{info.id}}">
	           <td><input type="checkbox"></td>
	           <#list columns as column>
	            <td>{{info.${column.columnName}}}</td> 
               </#list> 
          </tr>
     {{/each}}   
</script> 
<script type="text/javascript">
   $(document).ready(function(){  
	     loadData(); 
   }); 
   function loadData(){ 
	  getPages(ctx+'/${className}/${className}List','${className}TableTmpl','${className}Table',$("#${className}Form").serialize());
   };  
    
   
   function add${className}(){ 
      iframeParentLayer('添加${title}','740px','540px',ctx+'/${className}/toEdit${className?cap_first}');
   }
   
   function edit${className}(){ 
      var ids = [];  
      $('tbody input[type=checkbox]:checked').each(function(){ 
		  ids.push($(this).val()); 
	  }); 
	 if(ids.length>1){
		  layer.msg("只能选择一个");
		  return false;
	  } 
	  if(ids.length==0){
		  layer.msg("请至少选择一个");
		  return false;
	  }   
      iframeParentLayer('编辑${title}','740px','540px',ctx+'/${className}/toEdit${className?cap_first}?id='+ids[0]);
   }
   
    function delete${className}(){  
    	  var ids = [];  
	      $('tbody input[type=checkbox]:checked').each(function(){ 
			  ids.push($(this).val()); 
		  }); 
		 if(ids.length>1){
			  layer.msg("只能选择一个");
			  return false;
		  } 
		  if(ids.length==0){
			  layer.msg("请至少选择一个");
			  return false;
		  }  
         parent.layer.confirm('请确认是否删除？',function(index){   
            currentPageLoading()
	        $.post(ctx+"/${className}/delete/ids[0]",function(data){
	               layer.closeAll();
			       if(data.status=="SUCCESS"){  
			            msg("删除成功");
						loadData(); 	 
			        }else{
			            parent.layer.alert(data.message); 
			        }
			  });    
	    });
    } 
   
</script>     
</body>
</html>
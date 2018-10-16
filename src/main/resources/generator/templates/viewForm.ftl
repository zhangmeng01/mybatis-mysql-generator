${contextPath}
<!DOCTYPE html>
<html lang="en">
<head> 
${common}
<script src="${"${"?html}${base}${"}"?html}/plugins/jquery/jquery.validate.min.js" type="text/javascript"></script>
<script src="${"${"?html}${base}${"}"?html}/static/js/common/messages_zh.min.js" type="text/javascript"></script>
<style>
	input.error{border:1px solid red !important;}
	select.error{border:1px solid red !important;}
	label.error{
		margin-left:2px;margin-top:5px;
		color:red;font-family:"Microsoft Yahei";
		font-size:11px;
	}
	input.valid{border:1px solid #66AB9F !important;}
	select.valid{border:1px solid #66AB9F !important;}
</style>
</head>
<body>
<form autocomplete="off" id="${className}Form"  method="post" class="form-horizontal">
<#list columns as column>
    <div class="control-group">
        <label class="control-label" >${column.columnComment}：</label>
        <div class="controls">
            <input maxlength="100" class="input" id="${column.columnName}" name="${column.columnName}" type="text" value="${"${"?html}${className}${"Bean."?html}${column.columnName}${"}"?html}" />
        </div>
    </div>
</#list>
<div class="control-group" style="margin-bottom: 120px;">
<label class="control-label" > <button style="margin-left:317px;" class="btn_orange_small" type="submit">保存</button></label>
</div>
</form>
<script type="text/javascript">
$(function () {
	var validate = $("#${className}Form").validate({
	debug:false,
	submitHandler: function(form){
	//防止表单重复提交
	  $(form).find(":submit").attr("disabled", true).attr("value","Submitting...");
	  form.submit();
	},
	rules:{
		<#list columns as column>
			<#if column_index==0>${column.columnName}:{required:true}<#else>,${column.columnName}:{required:true}</#if>
		</#list>
	 },
	messages:{}
	});

});

</script>
</body>
</html>
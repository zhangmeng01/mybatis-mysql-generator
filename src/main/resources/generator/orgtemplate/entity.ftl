package ${package}.bean;
import lombok.Data;
import com.syswin.systoon.framework.bean.BaseBean;
<#assign upClassName=className?cap_first>
import java.util.Date;

@Data
public class ${upClassName} extends BaseBean {

<#list columns as column>
    private ${column.dataType} ${column.columnName}; //${column.columnComment}
</#list> 
 
}
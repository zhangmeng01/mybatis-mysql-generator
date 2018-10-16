package ${package}.entity.${module};
import com.qitoon.framework.core.entity.BaseEntity;
import java.util.Date;
<#assign upClassName=className?cap_first>  
public class ${upClassName} extends BaseEntity<${upClassName}> {
     
<#list columns as column>
    private ${column.dataType} ${column.columnName}; //${column.columnComment}
</#list> 
	
 <#list columns as column>
    public ${column.dataType} get${column.columnName?cap_first}(){  
        return ${column.columnName};  
    }
      
   public void set${column.columnName?cap_first}(${column.dataType} ${column.columnName}){  
     this.${column.columnName} = ${column.columnName};  
    }  
</#list>
}
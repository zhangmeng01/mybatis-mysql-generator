package ${package}.persistence.dao.mysql;
<#assign upClassName=className?cap_first>  
import ${package}.bean.${className?cap_first};
import java.util.List;

public interface I${upClassName}Dao{

     /**
     * 按主键查询
     * @param id
     * @return
     */
    public ${upClassName} get${upClassName}ById(Long id);
    /**
     * 按主键集合查询
     * @param ids
     * @return
     */
    public List<${upClassName}> get${upClassName}ByIds(Long[] ids);
    /**
     * 按条件查询
     * @param ${className}
     * @return
     */
    public ${upClassName} get${upClassName}(${upClassName} ${upClassName?uncap_first});
    /**
     * 查询所有记录
     * @return
     */
    public List<${upClassName}> findAllList();
    /**
     * 按条件查询
     * @param ${className}
     * @return
     */
    public List<${upClassName}> findList(${upClassName} ${upClassName?uncap_first});
    /**
     * 按条件获取数量
     * @param ${className}
     * @return
     */
    public int getCount(${upClassName} ${upClassName?uncap_first});
    /**
     * 插入操作
     * @param ${className}
     * @return
     */
    public int insert(${upClassName} ${upClassName?uncap_first});
    /**
     * 根据主键更新
     * @param ${className}
     * @return
     */
    public int updateById(${upClassName} ${upClassName?uncap_first});
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    public int deleteById(Long id);
    /**
     * 根据主键集合删除
     * @param ids
     * @return
     */
    public int deleteByIds(Long[] ids);
    /**
     * 根据条件删除
     * @param ${className}
     * @return
     */
    public int delete(${upClassName} ${upClassName?uncap_first});
}
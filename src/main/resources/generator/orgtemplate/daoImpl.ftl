package ${package}.dao.mysql;
<#assign upClassName=className?cap_first>
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ${package}.persistence.dao.I${upClassName}Dao;
import ${package}.bean.${className?cap_first};
import java.util.List;

@Repository
public class ${upClassName}DaoImpl implements I${upClassName}Dao{

    private static final String NAME_SPACE = "${upClassName}";

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public ${upClassName} get${upClassName}ById(Long id) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + ".getById", id);
    }

    @Override
    public List<${upClassName}> get${upClassName}ByIds(Long[] ids) {
        return sqlSessionTemplate.selectList(NAME_SPACE + ".getByIds", ids);
    }

    @Override
    public ${upClassName} get${upClassName}(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + ".get", ${upClassName?uncap_first});
    }

    @Override
    public List<${upClassName}> findAllList() {
        return sqlSessionTemplate.selectList(NAME_SPACE + ".findAllList");
    }

    @Override
    public List<${upClassName}> findList(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.selectList(NAME_SPACE + ".findList", ${upClassName?uncap_first});
    }

    @Override
    public int getCount(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.selectOne(NAME_SPACE + ".getCount", ${upClassName?uncap_first});
    }

    @Override
    public int insert(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.insert(NAME_SPACE + ".insert", ${upClassName?uncap_first});
    }

    @Override
    public int updateById(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.update(NAME_SPACE + ".update", ${upClassName?uncap_first});
    }

    @Override
    public int deleteById(Long id) {
        return sqlSessionTemplate.delete(NAME_SPACE + ".deleteById", id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return sqlSessionTemplate.delete(NAME_SPACE + ".deleteByIds", ids);
    }

    @Override
    public int delete(${upClassName} ${upClassName?uncap_first}) {
        return sqlSessionTemplate.delete(NAME_SPACE + ".delete", ${upClassName?uncap_first});
    }
   
}
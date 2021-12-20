package ${mapperPackage};

import org.apache.ibatis.annotations.Mapper;
import com.wakedata.common.mybatis.plus.mapper.CommonMapper;
import ${doPackage}.${entity}DO;

/**
 * @Description: ${entity} Mapper
 * @author: ${author}
 * @Date: ${date}
 */
@Mapper
public interface ${entity}Mapper extends CommonMapper<${entity}DO> {

}

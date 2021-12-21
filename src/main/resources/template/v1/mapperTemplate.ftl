package ${mapperPath};

import org.apache.ibatis.annotations.Mapper;
import com.wakedata.common.mybatis.plus.mapper.CommonMapper;
import ${doPath}.${entity}DO;

/**
 * @description:
 * @author: ${author}
 * @date: ${date}
 */
@Mapper
public interface ${entity}Mapper extends CommonMapper<${entity}DO> {

}

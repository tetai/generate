package ${queryPageHandlerPath};

import ${queryPagePath}.${entity}PageQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.wakedata.common.dto.ResultDTO;

/**
 * @description:
 * @author: ${author}
 * @date: ${date}
 */
@Slf4j
@Component
public class ${entity}PageQueryHandler {

    public ResultDTO handle(${entity}PageQuery query) {
        return null;
    }


}

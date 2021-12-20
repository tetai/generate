package cn.zkz.generate.model;

import lombok.Data;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/16
 */
@Data
public class TableField {

    private boolean keyIdentityFlag;
    private String name;
    private String type;
    private String propertyName;
    private DbColumnType columnType;
    private String comment;

    public String getPropertyType() {
        return null != this.columnType ? this.columnType.getType() : null;
    }
}

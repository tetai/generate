package cn.zkz.generate.model;

import lombok.Data;

import java.util.List;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/16
 */
@Data
public class TableInfo {

    private String name;
    private String comment="";
    private List<TableField> fields;


}

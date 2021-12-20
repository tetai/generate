package cn.zkz.generate.database;

import cn.zkz.generate.config.DatabaseConfig;
import cn.zkz.generate.model.TableField;
import cn.zkz.generate.utils.CommonUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/17
 */
public class InitMetaData {

    private static Connection conn = null;
    private static DatabaseConfig databaseConfig = DatabaseConfig.newInstance();

    public static Connection getConn() {
        if(conn != null){
            return conn;
        }
        try {
            Class.forName(databaseConfig.getDriver());
            Properties props =new Properties();
            props.setProperty("user", databaseConfig.getUsername());
            props.setProperty("password", databaseConfig.getPasswork());
            props.setProperty("remarks", "true");
            props.setProperty("useInformationSchema", "true");//mysql设置可以获取tables remarks信息
            props.setProperty("driver", databaseConfig.getDriver());
            conn = DriverManager.getConnection(databaseConfig.getUrl(), props);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static List<TableField> getTableField(String tableName) {
        List<TableField> tbColumns = new ArrayList();
        //与数据库的连接
        Connection conn = getConn();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            ResultSet rsKey = conn.getMetaData().getPrimaryKeys(null,null,tableName);
            String keyName=null;
            while(rsKey.next()){
                keyName = rsKey.getString("COLUMN_NAME").toLowerCase();
                keyName = CommonUtils.getNoUnderlineStr(keyName);
            }
            rs = conn.getMetaData().getColumns(   null, getSchema(conn),tableName.toUpperCase(), "%");
            while(rs.next()){
                TableField tbfrField = new TableField();
                String fieldNm = rs.getString("COLUMN_NAME").toLowerCase();
                tbfrField.setName(fieldNm);//表字段名
                tbfrField.setPropertyName(CommonUtils.getNoUnderlineStr(fieldNm));//字段名
                tbfrField.setComment(rs.getString("REMARKS"));//字段注释
                tbfrField.setType(rs.getString("TYPE_NAME"));//字段类型
                tbfrField.setColumnType(CommonUtils.processTypeConvert(tbfrField.getType()));

                if(keyName!=null && keyName.equals(tbfrField.getName())){
                    tbfrField.setKeyIdentityFlag(true);
                }else{
                    tbfrField.setKeyIdentityFlag(false);
                }

                tbColumns.add(tbfrField);
            }
            if(stm!=null){
                stm.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("getColumnNames failure", e);
        } catch (Exception e) {
            throw new RuntimeException("Exception rs failure", e);
        }
        return tbColumns;
    }



    private static String getSchema(Connection conn) throws Exception {
        return conn.getMetaData().getUserName().toUpperCase();

    }

}

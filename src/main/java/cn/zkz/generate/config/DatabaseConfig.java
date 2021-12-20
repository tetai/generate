package cn.zkz.generate.config;

import lombok.Data;
import lombok.ToString;

import java.util.Properties;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/16
 */
@Data
@ToString
public class DatabaseConfig {

    private static final DatabaseConfig databaseConfig = new DatabaseConfig();

    private DatabaseConfig(){}

    public static DatabaseConfig newInstance() {
        return databaseConfig;
    }

    private String driver;

    private String url;

    private String username;

    private String passwork;


    public static void buildData(Properties properties) {
        DatabaseConfig databaseConfig = newInstance();
        databaseConfig.driver = properties.getProperty("database.driver");
        databaseConfig.url = properties.getProperty("database.url");
        databaseConfig.username = properties.getProperty("database.username");
        databaseConfig.passwork = properties.getProperty("database.passwork");
    }

}

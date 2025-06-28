package com.uch.finalproject.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseUtil {
    
    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${spring.datasource.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    /**
     * 取得資料庫連接
     * @return Connection 資料庫連接物件
     * @throws SQLException SQL 例外
     * @throws ClassNotFoundException 驅動程式載入例外
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
    
    /**
     * 關閉資料庫相關資源
     * @param connection 連接物件
     * @param statement Statement 物件
     * @param resultSet ResultSet 物件
     */
    public void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    // 記錄錯誤但不拋出例外
                    System.err.println("關閉資源時發生錯誤: " + e.getMessage());
                }
            }
        }
    }
}
package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.dto.FoodDetailResponse;
import com.uch.finalproject.model.dto.FoodResponse;
import com.uch.finalproject.model.dto.StringArrayResponse;
import com.uch.finalproject.model.entity.FoodDetailEntity;

import com.uch.finalproject.util.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/food")
public class DemoRemoteSelect {   

    @Autowired
    private DatabaseUtil databaseUtil;

    @RequestMapping(value = "/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringArrayResponse getFoodName(String keyword) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = databaseUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select fd.name from food_detail fd where fd.name like '%" + keyword + "%'");
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()) {
                
                data.add(rs.getString("name"));
            }

            return new StringArrayResponse(0, "成功", data);
        } catch(SQLException e) {
            return new StringArrayResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new StringArrayResponse(1, "無法註冊驅動程式", null);
        }finally {
            databaseUtil.closeResources(rs, stmt, conn);
        }
    }
}

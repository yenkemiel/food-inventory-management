package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.dto.ProductResponse;
import com.uch.finalproject.model.entity.ProductEntity;

import com.uch.finalproject.util.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;

// 註釋annotation

@RestController
public class ProductController {

    @Autowired
    private DatabaseUtil databaseUtil;

    // API入口
    @RequestMapping(value="/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse Product() {
        return getProductList();
    }

    // 取得產品清單並回傳ResponseProductEntity物件
    private ProductResponse getProductList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = databaseUtil.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select food_id, name, category, calories,protein, saturated_fat, total_carbohydrates, dietary_fiber from food_detail fd join category c on fd.category_no=c.category_no where food_id =");

            ArrayList<ProductEntity> products = new ArrayList<>();

            while(rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(rs.getInt("id"));
                productEntity.setName(rs.getString("name"));
                productEntity.setDescription(rs.getString("description"));
                productEntity.setPrice(rs.getInt("price"));
                productEntity.setImageUrl(rs.getString("image_url"));
                productEntity.setStoreName(rs.getString("store_name"));
                productEntity.setCategory(rs.getString("category"));

                // 將取的商品資料存到ArrayList
                products.add(productEntity);
            }

            return new ProductResponse(0, "sucess", products);


        } catch(ClassNotFoundException e) {
            // 無法註冊
            return new ProductResponse(1, "無法註冊", null);
        } catch(SQLException e) {
            return new ProductResponse(e.getErrorCode(), 
                    e.getMessage(), null);
        }finally {
            databaseUtil.closeResources(rs, stmt, conn);
        }
        
    }
}

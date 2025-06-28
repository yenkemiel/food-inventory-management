package com.uch.finalproject.model.dto;


import lombok.Data;

import java.util.ArrayList;

import com.uch.finalproject.model.entity.FoodEntity;
import com.uch.finalproject.model.entity.FoodPageEntity;

@Data
public class FoodPageResponse extends BaseResponse {
    FoodPageEntity data;

    public FoodPageResponse(int code, String message, ArrayList<FoodEntity>  foods, int total) {
        super(code, message);

        this.data = new FoodPageEntity();
        this.data.foods = foods;
        this.data.total = total;
    }
}

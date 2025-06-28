package com.uch.finalproject.model.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FoodDetailListPageEntity {
    public ArrayList<FoodDetailEntity> foods;
    public int total;
}

package com.uch.finalproject.model.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FoodPageEntity {
    public ArrayList<FoodEntity> foods;
    public int total;
}

package com.uch.finalproject.model.entity;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ToBuyPageEntity {
    public ArrayList<ToBuyEntity> foods;
    public int total;
}

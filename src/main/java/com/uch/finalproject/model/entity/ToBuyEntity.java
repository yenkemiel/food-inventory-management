package com.uch.finalproject.model.entity;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class ToBuyEntity {
    int tobuyId;
    int foodId;
    Date tobuyDate;
    String name;
}


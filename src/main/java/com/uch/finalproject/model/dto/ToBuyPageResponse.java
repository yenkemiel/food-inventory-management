package com.uch.finalproject.model.dto;


import lombok.Data;

import java.util.ArrayList;

import com.uch.finalproject.model.entity.ToBuyEntity;
import com.uch.finalproject.model.entity.ToBuyPageEntity;

@Data
public class ToBuyPageResponse extends BaseResponse {
    ToBuyPageEntity data;

    public ToBuyPageResponse(int code, String message, ArrayList<ToBuyEntity> foods, int total) {
        super(code, message);

        this.data = new ToBuyPageEntity();
        this.data.foods = foods;
        this.data.total = total;
    }
}

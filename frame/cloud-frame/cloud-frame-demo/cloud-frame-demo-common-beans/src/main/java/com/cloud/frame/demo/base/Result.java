package com.cloud.frame.demo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wd on 2018/3/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Boolean success = true;
    private String code;
    private String message;
    private T body;

    public static Result build() {
        return new Result();
    }
}

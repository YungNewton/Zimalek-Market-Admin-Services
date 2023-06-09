package com.zmarket.marketadminservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiFieldError {

    private String field;
    private String message;
    private Object rejectedValue;

}

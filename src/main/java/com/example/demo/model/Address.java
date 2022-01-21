package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @fangomez
 */
@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String postCode;
}

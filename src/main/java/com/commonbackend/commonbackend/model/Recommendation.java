package com.commonbackend.commonbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    int recommendationId;
    int productId;
    int rating;
    String comment;
    String email;

}

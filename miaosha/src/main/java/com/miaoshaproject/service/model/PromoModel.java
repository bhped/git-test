package com.miaoshaproject.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class PromoModel {
    private Integer id;
    private Integer status;
    private String promoName;
    private DateTime startDate;
    private DateTime endDate;
    private Integer itemId;
    private BigDecimal promoItemPrice;
}

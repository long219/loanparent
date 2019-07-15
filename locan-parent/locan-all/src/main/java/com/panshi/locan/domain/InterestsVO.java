package com.panshi.locan.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Data
@Alias("interests")
public class InterestsVO {

    private Integer id ; //利息id
    private BigDecimal interest; //利率

}

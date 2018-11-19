package com.sainsburys.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.sainsburys.model.Product;

public class CalculateTotal {
	
	public static List<BigDecimal> calculateTotalGross(List<Product> products){
        List<BigDecimal> calculatedTotals = new ArrayList<BigDecimal>();
        
		BigDecimal total = products.stream()
                .map(Product::getUnit_price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        calculatedTotals.add(total
        		.setScale(2, RoundingMode.UP));
        
        calculatedTotals.add(total
        		.multiply(BigDecimal.valueOf(Constants.PRODUCT_VAT)).setScale(2, RoundingMode.UP));
        
        return calculatedTotals;
	}

}

package com.sainsburys.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.sainsburys.model.Product;

public class CalculateTotal {
	
	public static List<BigDecimal> calculateTotalGross(List<Product> products){
        List<BigDecimal> calculatedTotals = new ArrayList<BigDecimal>();
        
		BigDecimal gross = BigDecimal.valueOf(0);
		BigDecimal vat = BigDecimal.valueOf(0);
		
		calculatedTotals.add(gross);
		calculatedTotals.add(vat);
        
        return calculatedTotals;
	}

}

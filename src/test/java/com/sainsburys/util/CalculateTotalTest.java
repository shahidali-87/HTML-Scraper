package com.sainsburys.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import org.junit.Before;
import org.junit.Test;

import com.sainsburys.model.Product;

public class CalculateTotalTest {
	
	String title;
	BigDecimal unit_price;
	OptionalInt kcal_per_100g;
	String description;
	
	String title2;
	BigDecimal unit_price2;
	OptionalInt kcal_per_100g2;
	String description2;
	
	List<Product> products = new ArrayList<Product>();
	
	@Before
	public void setUp() throws Exception{
		
		title = "Blueberries and stuff 400g";
		unit_price = new BigDecimal(10.44);
		kcal_per_100g = OptionalInt.of(100);
		description = "this is a description";
		
		Product product1 = new Product(title, unit_price, kcal_per_100g, description);
		
		products.add(product1);
		
		title2 = "some title";
		unit_price2 = new BigDecimal(50.5);
		kcal_per_100g2 = OptionalInt.empty();
		description2 = "blueberries desc";
		
		Product product2 = new Product(title2, unit_price2, kcal_per_100g2, description2);
		
		products.add(product2);
	}
	
	@Test
    public void testcalculateTotalGrossEmptyProductList()
    {
		List<Product> products = new ArrayList<Product>();
		
		List<BigDecimal> totals = CalculateTotal.calculateTotalGross(products);
		BigDecimal grossTotal = totals.get(0);
		BigDecimal vatTotal = totals.get(1);
		
		assertEquals(BigDecimal.valueOf(0).setScale(2, RoundingMode.UP), grossTotal);
		assertEquals(BigDecimal.valueOf(0).setScale(2, RoundingMode.UP), vatTotal);
    }
    
	@Test
    public void testcalculateTotalGrossWithProductList()
    {   
		List<BigDecimal> totals = CalculateTotal.calculateTotalGross(products);
		BigDecimal grossTotal = totals.get(0);
		BigDecimal vatTotal = totals.get(1);
		
		assertEquals(BigDecimal.valueOf(60.94).setScale(2, RoundingMode.UP), grossTotal);
		assertEquals(BigDecimal.valueOf(12.19).setScale(2, RoundingMode.UP), vatTotal);
    }

}

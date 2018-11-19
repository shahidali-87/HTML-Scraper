package com.sainsburys.util;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import org.junit.Before;
import org.junit.Test;

import com.sainsburys.model.Product;

public class JsonMapperTest {
	
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
    public void testjsonMapperEmptyProductList()
    {
		List<Product> products = new ArrayList<Product>();
		
		String result = JsonMapper.jsonMapper(products);
		
		//TO DO: better test
		assertNotNull(result);
    }
    
	@Test
    public void testjsonMapperWithProductList()
    {	
		String result = JsonMapper.jsonMapper(products);
		
		//TO DO: better test
		assertNotNull(result);
    }

}

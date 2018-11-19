package com.sainsburys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import org.junit.Before;
import org.junit.Test;

import com.sainsburys.model.Product;
import com.sainsburys.util.Constants;

public class HtmlScraperTest {
	
	String title;
	BigDecimal unit_price;
	OptionalInt kcal_per_100g;
	String description;
	
	String title2;
	BigDecimal unit_price2;
	OptionalInt kcal_per_100g2;
	String description2;
	
	List<Product> products = new ArrayList<Product>();
	
	public static String URL = Constants.URL;
	
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
    public void testGetProductsCorrectValidURL()
    {
        List<Product> products = HtmlScraper.getProducts(URL);
        
        assertEquals("Sainsbury's Strawberries 400g" ,products.get(0).getTitle());
        assertEquals(OptionalInt.of(33),products.get(0).getKcal_per_100g());
        assertEquals(BigDecimal.valueOf(1.75) ,products.get(0).getUnit_price());
        assertEquals("by Sainsbury's strawberries" ,products.get(0).getDescription());
    }

	@Test
    public void testGetProductsIncorrectValidURL()
    {
		List<Product> products = HtmlScraper.getProducts("http://www.google.com");
		
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

	@Test(expected = IllegalArgumentException.class)
    public void testGetProductsInvalidURL()
    {
		HtmlScraper.getProducts("fqfwqdfwqf");
    }
	
	@Test(expected = java.lang.IllegalArgumentException.class) //put null pointer, commit, then switch
    public void testGetProductsNullURL()
    {
		HtmlScraper.getProducts(null);
    }

}

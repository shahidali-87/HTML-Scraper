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
	
	@Test(expected = java.lang.IllegalArgumentException.class)
    public void testGetProductsNullURL()
    {
		HtmlScraper.getProducts(null);
    }

}

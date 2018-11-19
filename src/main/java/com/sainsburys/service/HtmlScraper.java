package com.sainsburys.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsburys.model.Product;

public class HtmlScraper {
	
    public static List<Product> getProducts(String url) {
    	
    	List<Product> products = new ArrayList<Product>();
    	
    	try {
	    	Document document = Jsoup.connect(url).get();
			
			Elements productElements = document.select(".product");
			
			String title;
			BigDecimal unit_price;
			OptionalInt kcal_per_100g;
			String description;
			String productLink;
			
			for (Element e : productElements){
				title = e.getElementsByTag("a").first().text();
				unit_price = new BigDecimal(e.selectFirst(".pricePerUnit").text().replace("£", "").replace("/unit", ""));
				
				productLink = e.select("a").attr("abs:href");
				
				kcal_per_100g = getKcalPer100g(Jsoup.connect(productLink).get());
				description = getDescription(Jsoup.connect(productLink).get());
				
				products.add(new Product(title, unit_price, kcal_per_100g, description));
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}

        return products;
    }

    private static OptionalInt getKcalPer100g(Document document) throws IOException {
        Elements nutritionInfo = document.select(".tableRow0");
        
        if (nutritionInfo.isEmpty()){
        	return null;
        }
        
        String kcalPer100g = nutritionInfo.first().text();
        
        return OptionalInt.of(Integer.parseInt(kcalPer100g.substring(0, kcalPer100g.indexOf("kcal"))));
    }

    private static String getDescription(Document document) throws IOException {
		Element desc = document.selectFirst("div[class*='productText'] > p");
		if(desc == null) {
			desc = document.selectFirst(".memo > p");	
		}
		return desc.text();
    }
}

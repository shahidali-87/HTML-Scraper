package com.sainsburys.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sainsburys.model.Product;

public class JsonMapper {

	public static String jsonMapper(List<Product> products){
		
		JsonArray jsonProducts = new JsonArray();
		JsonObject results = new JsonObject();
		JsonObject jsonTotal = new JsonObject();
        
		for(Product product : products) { 
			JsonObject jsonProduct = new JsonObject();
			
			jsonProduct.addProperty("title", product.getTitle());
			
  	        if (product.getKcal_per_100g() != null && product.getKcal_per_100g().isPresent()){
  	        	int kcalper100 = product.getKcal_per_100g().getAsInt();
  	        	jsonProduct.addProperty("kcal_per_100g", kcalper100);
  	        }
  	        
  	        jsonProduct.addProperty("unit_price", product.getUnit_price().setScale(2, RoundingMode.UP));
  	        jsonProduct.addProperty("description", product.getDescription());
  	        jsonProducts.add(jsonProduct);
        }
        
		List<BigDecimal> totals = CalculateTotal.calculateTotalGross(products);
		BigDecimal grossTotal = totals.get(0);
		BigDecimal vatTotal = totals.get(1);
		jsonTotal.addProperty("gross",grossTotal); 
		jsonTotal.addProperty("vat",vatTotal); 
		
		results.add("results", jsonProducts);
		results.add("total", jsonTotal);
        
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(results);		
	}
}
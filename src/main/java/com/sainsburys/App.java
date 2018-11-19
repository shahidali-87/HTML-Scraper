package com.sainsburys;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sainsburys.model.Product;
import com.sainsburys.service.HtmlScraper;
import com.sainsburys.util.Constants;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	
    	List<Product> products = HtmlScraper.getProducts(Constants.URL);
    }
}

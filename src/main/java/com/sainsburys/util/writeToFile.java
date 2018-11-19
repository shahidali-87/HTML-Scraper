package com.sainsburys.util;

import java.io.FileWriter;
import java.io.IOException;

public class writeToFile {
	
	private static final String outputFile = Constants.OUTPUT_FILE;

    public static void writeToJsonFile(String data) {   

		try (FileWriter file = new FileWriter(outputFile)){
			file.write(data);
		}
		catch (IOException e) {
			e.printStackTrace();
			}
    }

}

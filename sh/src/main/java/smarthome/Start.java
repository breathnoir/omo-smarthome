package smarthome;

import com.fasterxml.jackson.databind.ObjectMapper;
import smarthome.config.HouseConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Start {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        String configFile = "houseconfig.json";

        try {
            InputStream inputStream = Start.class.getClassLoader().getResourceAsStream(configFile);

            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + configFile);
            }

            HouseConfig houseConfig = mapper.readValue(inputStream, HouseConfig.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

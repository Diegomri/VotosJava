package com.votos.v2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class ReadCSVService {

    public List<Map<String, String>> readCsvContent() {
        BufferedReader reader = null;
        List<Map<String, String>> result = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("static/Resultados.csv");
            InputStream inputStream = resource.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String headerLine = reader.readLine();
            if (headerLine == null) return result;

            String[] headers = headerLine.split(",");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length && i < data.length; i++) {
                    row.put(headers[i], data[i]);
                }
                result.add(row);
            }
            return result;
        } catch (IOException e) {
            return Collections.emptyList();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
package org.softuni.exercises.util;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class HtmlRiderImpl implements HtmlRider {
    @Override
    public String readHtmlFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path)
                        )
                )
        );

        StringBuilder htmlContent = new StringBuilder();
        String htmlLine;

        while ((htmlLine = reader.readLine()) != null){
            htmlContent.append(htmlLine).append(System.lineSeparator());
        }

        return htmlContent.toString().trim();
    }
}

package com.data.extractor.util;

import com.data.extractor.dto.Outcome;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonFileBuilder {

    public static File buildJsonFile(List<Outcome> outcomeList) {
        File outputFile = new File("Outcome.json");

        try (FileWriter file = new FileWriter(outputFile)) {
            file.write("[\n");

            for (int index=0; index < outcomeList.size(); index++) {
                file.write("  {\n");
                Outcome outcome = outcomeList.get(index);
                file.write("    \"uuid\": \"" + outcome.uuid() + "\",\n");
                file.write("    \"id\": \"" + outcome.id() + "\",\n");
                file.write("    \"name\": \"" + outcome.name() + "\",\n");
                file.write("    \"likes\": \"" + outcome.likes() + "\",\n");
                file.write("    \"transport\": \"" + outcome.rides() + "\",\n");
                file.write("    \"avgSpeed\": " + outcome.avgSpeed() + ",\n");
                file.write("    \"topSpeed\": " + outcome.topSpeed() + "\n");
                boolean lastItem = outcomeList.size() - 1 == index;
                file.write("  }" + (lastItem ? "" : ",") + "\n");
            }
            file.write("]");
            return outputFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

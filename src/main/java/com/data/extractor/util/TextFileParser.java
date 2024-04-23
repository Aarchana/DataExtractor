package com.data.extractor.util;

import com.data.extractor.dto.Outcome;
import com.data.extractor.exception.AppErrorCode;
import com.data.extractor.exception.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextFileParser {

    public static List<Outcome> parse(byte[] bytes) {
        List<Outcome> outcomeList = new ArrayList<>();
        int lineNumber = 1;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                Outcome outcome = new Outcome(parts[0], parts[1], parts[2], parts[3], parts[4], Float.parseFloat(parts[5]), Float.parseFloat(parts[6]));
                outcomeList.add(outcome);
                lineNumber++;
            }
        } catch (IOException e) {
            throw new InvalidFileFormatException(AppErrorCode.INVALID_FILE_FORMAT, "file contents are invalid at line" + lineNumber);
        }
        return outcomeList;

    }
}

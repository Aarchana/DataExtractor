package com.data.extractor.dto;

public record Outcome(String uuid,
                      String id,
                      String name,
                      String likes,
                      String rides,
                      Float avgSpeed,
                      Float topSpeed) {
}

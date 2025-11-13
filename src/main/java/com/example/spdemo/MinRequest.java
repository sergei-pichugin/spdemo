package com.example.spdemo;

public class MinRequest {

    private final String filePath;
    private final int order;

    public MinRequest(String filePath, int order) {
        this.filePath = filePath;
        this.order = order;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getOrder() {
        return order;
    }
}

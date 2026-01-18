package com.pafiast.solid.lsp.good;

public class SimpleDocument implements WritableDocument {

    private String content;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}


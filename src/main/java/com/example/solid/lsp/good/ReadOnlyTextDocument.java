package com.example.solid.lsp.good;

public class ReadOnlyTextDocument implements ReadableDocument {

    private final String content;

    public ReadOnlyTextDocument(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}


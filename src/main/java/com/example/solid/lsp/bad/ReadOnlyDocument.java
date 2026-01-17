package com.example.solid.lsp.bad;

public class ReadOnlyDocument extends Document {

    @Override
    public void setContent(String content) {
        throw new UnsupportedOperationException("Read-only document");
    }
}


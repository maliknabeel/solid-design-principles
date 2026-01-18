package com.pafiast.solid.lsp.good;

public interface WritableDocument extends ReadableDocument {

    void setContent(String content);
}


package com.pafiast.solid.lsp.good;

/**
 * Implementation of a writable document.
 * Adheres to LSP by implementing {@link WritableDocument}.
 */
public class SimpleDocument implements WritableDocument {

    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }
}


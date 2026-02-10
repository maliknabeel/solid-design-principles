package com.pafiast.solid.lsp.good;

/**
 * Implementation of a read-only document.
 * Adheres to LSP by only implementing {@link ReadableDocument}.
 */
public class ReadOnlyTextDocument implements ReadableDocument {

    private final String content;

    /**
     * Creates a read-only document with initial content.
     *
     * @param content the content
     */
    public ReadOnlyTextDocument(String content) {
        this.content = content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContent() {
        return content;
    }
}


package com.pafiast.solid.lsp.good;

/**
 * Interface for documents that can be written to.
 * Extends {@link ReadableDocument} to include writing capabilities.
 */
public interface WritableDocument extends ReadableDocument {

    /**
     * Sets the document content.
     *
     * @param content the new content
     */
    void setContent(String content);
}


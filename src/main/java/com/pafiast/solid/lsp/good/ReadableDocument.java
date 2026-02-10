package com.pafiast.solid.lsp.good;

/**
 * Interface for documents that can be read.
 * Represents the read-only capability.
 */
public interface ReadableDocument {

    /**
     * Retrieves the document content.
     *
     * @return the content
     */
    String getContent();
}


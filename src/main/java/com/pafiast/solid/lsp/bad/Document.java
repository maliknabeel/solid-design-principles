package com.pafiast.solid.lsp.bad;

/**
 * Base document class.
 * Defines methods for getting and setting content.
 */
public class Document {

    private String content;

    /**
     * Retrieves the document content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the document content.
     *
     * @param content the new content
     */
    public void setContent(String content) {
        this.content = content;
    }
}


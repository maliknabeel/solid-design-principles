package com.pafiast.solid.lsp.bad;

/**
 * Bad example of Liskov Substitution Principle (LSP).
 * <p>
 * This class violates LSP because it extends {@link Document} but throws an exception
 * for {@code setContent}, breaking the contract established by the parent class.
 * Code expecting a {@link Document} may fail when working with this subclass.
 */
public class ReadOnlyDocument extends Document {

    /**
     * Throws an exception because this document is read-only.
     *
     * @param content the content to set
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setContent(String content) {
        throw new UnsupportedOperationException("Read-only document");
    }
}


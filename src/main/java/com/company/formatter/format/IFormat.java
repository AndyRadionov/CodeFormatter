package com.company.formatter.format;

import java.util.List;

/**
 * Format class loads and contains current formatter properties.
 */
public interface IFormat {
    /**
     * Getter for special symbols which needs to be wrapped with space.
     *
     * @return List of Characters
     */
    List<Character> getSpecialSymbols();

    /**
     * Getter for new line offset symbol.
     *
     * @return char offset symbol
     */
    char getOffsetSymbol();

    /**
     * Getter for new line offset step.
     *
     * @return offset step number
     */
    int getOffsetStep();
}

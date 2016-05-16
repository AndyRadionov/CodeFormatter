package com.company.formatter.context;

import java.util.List;

/**
 * Interface for formatter context.
 */
public interface IContext {

    /**
     * Increasing new line offset.
     */
    void increaseOffset();

    /**
     * Decreasing new line offset.
     *
     * @throws ContextException if offset level == 0
     */
    void decreaseOffset() throws ContextException;

    /**
     * Increasing parentheses nesting level.
     */
    void increaseParenthesesLevel();

    /**
     * Decreasing parentheses nesting level.
     *
     * @throws ContextException parentheses nesting level == 0
     */
    void decreaseParenthesesLevel() throws ContextException;

    /**
     * Checks all types of brackets balances.
     *
     * @throws ContextException if brackets balance is wrong
     */
    void checkIsEnoughBrackets() throws ContextException;

    /**
     * Calculates and returns current offset String.
     *
     * @return current offset String
     * @throws ContextException if offset level is wrong
     */
    String getOffset() throws ContextException;

    /**
     * Getter for special symbols which needs to be wrapped with space.
     *
     * @return List of Characters
     */
    List<Character> getSpecialSymbols();

    /**
     * Getter for parentheses nesting level.
     *
     * @return int number of parentheses nesting level
     */
    int getParenthesesLevel();


}

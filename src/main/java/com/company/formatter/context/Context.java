package com.company.formatter.context;

import com.company.formatter.format.IFormat;

import java.util.List;

/**
 * Class contains current formatting context. Offset and parentheses level.
 */
public class Context implements IContext {
    private IFormat format;

    private int offsetCount;
    private int parenthesesLevel;


    /**
     * Constructs new Context object containing IFormat object.
     *
     * @param format contains properties for Formatter
     */
    public Context(final IFormat format) {
        this.format = format;
    }


    /**
     * Increasing new line offset.
     */
    public void increaseOffset() {
        offsetCount++;
    }

    /**
     * Decreasing new line offset.
     *
     * @throws ContextException if offset level == 0
     */
    public void decreaseOffset() throws ContextException {
        if (offsetCount == 0) {
            throw new ContextException("Negative offset");
        }
        offsetCount--;
    }

    /**
     * Increasing parentheses nesting level.
     */
    public void increaseParenthesesLevel() {
        parenthesesLevel++;
    }

    /**
     * Decreasing parentheses nesting level.
     *
     * @throws ContextException parentheses nesting level == 0
     */
    public void decreaseParenthesesLevel() throws ContextException {
        if (parenthesesLevel == 0) {
            throw new ContextException("Too much close parenthesis symbols");
        }
        parenthesesLevel--;
    }

    /**
     * Checks all types of brackets balances.
     *
     * @throws ContextException if brackets balance is wrong
     */
    public void checkIsEnoughBrackets() throws ContextException {
        if (parenthesesLevel > 0) {
            throw new ContextException("Not enough Parentheses!");
        }
        if (offsetCount > 0) {
            throw new ContextException("Not enough Braces!");
        }
    }

    /**
     * Calculates and returns current offset String.
     *
     * @return current offset String
     * @throws ContextException if offset level is wrong
     */
    public String getOffset() throws ContextException {
        if (offsetCount < 0) {
            throw new ContextException("Wrong offset calculation!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offsetCount * format.getOffsetStep(); i++) {
            sb.append(format.getOffsetSymbol());
        }
        return sb.toString();
    }


    /**
     * Getter for special symbols which needs to be wrapped with space.
     *
     * @return List of Characters
     */
    public List<Character> getSpecialSymbols() {
        return format.getSpecialSymbols();
    }

    /**
     * Getter for parentheses nesting level.
     *
     * @return int number of parentheses nesting level
     */
    public int getParenthesesLevel() {
        return parenthesesLevel;
    }

}

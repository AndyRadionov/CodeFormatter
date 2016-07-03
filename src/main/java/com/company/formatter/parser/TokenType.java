package com.company.formatter.parser;

/**
 * Created by Andy on 16.06.2016.
 */
public enum TokenType {
    LINE_COMMENT,
    MULTILINE_COMMENT,
    OPEN_PAREN,
    CLOSE_PAREN,
    OPEN_BRACE,
    CLOSE_BRACE,
    OPERATOR,
    REGULAR_SYMBOL,
    END_LINE, LITERAL
}

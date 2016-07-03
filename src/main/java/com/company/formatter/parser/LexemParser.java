package com.company.formatter.parser;

import com.company.reader.IReader;
import com.company.reader.ReaderException;

/**
 * Created by Andy on 11.06.2016.
 */
public class LexemParser {
    private IReader reader;
    private boolean hasNext;
    private char symbol;

    public LexemParser(IReader reader) throws ParserException {
        this.reader = reader;
        if (tryHasNext()) hasNext = true;
        symbol = ' ';
    }

    public boolean hasNext() throws ParserException {
        if (!tryHasNext() && hasNext) {
            hasNext = false;
            return true;
        }
        return hasNext;
    }

    private char nextSymbol() throws ParserException {
        try {
            if (hasNext) return reader.read();
            return symbol;
        } catch (ReaderException e) {
            throw new ParserException(e);
        }
    }

    public Token getToken() throws ParserException {
        StringBuilder resultBuilder = new StringBuilder();

        skipWhiteSpaces();
        return parseSymbol(resultBuilder);
    }

    private void skipWhiteSpaces() throws ParserException {
        if (Character.isWhitespace(symbol)) {
            while (hasNext() && Character.isWhitespace(symbol = nextSymbol())) ;
        }
    }

    private Token parseSymbol(StringBuilder resultBuilder) throws ParserException {
        if (isQuoteSymbol(symbol)) {
            return parseLiteral(resultBuilder);
        }

        if (isDelim(symbol)) {
            parseDelimiter(resultBuilder);
            if (isLineComment(resultBuilder)) {
                return createLineComment(resultBuilder);
            } else if (isMultiLineComment(resultBuilder)) {
                return createMultiLineComment(resultBuilder);
            } /*else if (isOperator(resultBuilder.toString())) {
                return createOperator(resultBuilder);
            } */else {
                return createDelimiterToken(resultBuilder);
            }
        }
        return createRegularSymbol(resultBuilder);
    }

    private void parseDelimiter(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && (isDelim(symbol = nextSymbol()))) {
            if (isBracket() || isEndLine()) return;
            appendSymbol(resultBuilder);
            if(isOperator(resultBuilder.toString())) {
                symbol=' ';
                return;
            }
        }
    }
    private boolean isBracket() {
        return "(){}".contains(String.valueOf(symbol));
    }
    private boolean isEndLine() {
        return ';' == symbol;
    }
    private Token parseLiteral(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && !isQuoteSymbol(symbol = nextSymbol())) {
            appendSymbol(resultBuilder);
        }
        if (isQuoteSymbol(symbol)) {
            appendSymbol(resultBuilder);
        }
        symbol = ' ';
        return createToken(TokenType.LITERAL, resultBuilder);
    }

    private Token createOperator(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && isOperator(resultBuilder.toString() + (symbol = nextSymbol()))) {
            appendSymbol(resultBuilder);
        }
        return createToken(TokenType.OPERATOR, resultBuilder);
    }


    private Token createRegularSymbol(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && !isDelim(symbol = nextSymbol()) && !Character.isWhitespace(symbol)) {
            appendSymbol(resultBuilder);
        }
        return createToken(TokenType.REGULAR_SYMBOL, resultBuilder);
    }

    private void appendSymbol(StringBuilder resultBuilder) {
        resultBuilder.append(symbol);
    }

    private Token createLineComment(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && (symbol = nextSymbol()) != '\n') {
            appendSymbol(resultBuilder);
        }
        return createToken(TokenType.LINE_COMMENT, resultBuilder);

    }

    private Token createMultiLineComment(StringBuilder resultBuilder) throws ParserException {
        appendSymbol(resultBuilder);
        while (hasNext() && !resultBuilder.toString().endsWith("*/")) {
            symbol = nextSymbol();
            appendSymbol(resultBuilder);
        }
        symbol = ' ';
        return createToken(TokenType.MULTILINE_COMMENT, resultBuilder);

    }

    private Token createDelimiterToken(StringBuilder resultBuilder) {
        switch (resultBuilder.toString()) {
            case "(":
                return createToken(TokenType.OPEN_PAREN, resultBuilder);
            case ")":
                return createToken(TokenType.CLOSE_PAREN, resultBuilder);
            case "{":
                return createToken(TokenType.OPEN_BRACE, resultBuilder);
            case "}":
                return createToken(TokenType.CLOSE_BRACE, resultBuilder);
            case ";":
                return createToken(TokenType.END_LINE, resultBuilder);
        }
        return createToken(TokenType.OPERATOR, resultBuilder);
    }

    private Token createToken(TokenType type, StringBuilder resultBuilder) {
        return new Token(type, resultBuilder.toString().trim());
    }


    private boolean isQuoteSymbol(char symbol) {
        return '"' == symbol;
    }

    private boolean isLineComment(StringBuilder resultBuilder) {
        return ("//".equals(resultBuilder.toString()));
    }

    private boolean isMultiLineComment(StringBuilder resultBuilder) {
        return ("/*".equals(resultBuilder.toString()));
    }

    private boolean isDelim(char c) {
        return ("+-/*%^=(){};><&|".indexOf(c) != -1);
    }

    private boolean isOperator(String s) {
        return ("~ ! + ++ += - -- -= * *= / /= % %= & &= && | |= " +
                "|| ^ ^= > >= >> >>= >>> >>>= < <= << <<= == != ? :")
                .contains(s);
    }

    private boolean tryHasNext() throws ParserException {
        try {
            return reader.hasNext();
        } catch (ReaderException e) {
            throw new ParserException(e);
        }
    }
}

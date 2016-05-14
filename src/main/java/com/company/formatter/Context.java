package com.company.formatter;

import com.company.reader.IReader;
import com.company.reader.ReaderException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class for formatting symbols according to current format settings and current context
 */
public class Context implements IContext {
    private final String DEFAULT_PROPERTIES_FILE = "src/main/resources/java-format.properties";
    private char previousChar;
    private boolean hasPreviousChar = false;

    private List<Character> specialSymbols = new ArrayList<>();

    private char offsetSymbol;
    private int offsetStep;
    private int offsetCount;
    private char openBrace;
    private char closeBrace;
    private int parenthesesLevel;
    private char newLineSymbol;
    private char spaceSymbol;
    private char endLineSymbol;
    private char openParenthesis;
    private char closeParenthesis;
    private char dot = '.';

    /**
     * Constructs new Context file from default properties file
     *
     * @throws ContextException if default properties file cannot be read
     */
    public Context() throws ContextException {
        loadProperties(DEFAULT_PROPERTIES_FILE);
    }

    /**
     * Constructs new Context file from given properties file
     *
     * @param propertiesFilesName properties file
     * @throws ContextException if given properties file cannot be read
     */
    public Context(String propertiesFilesName) throws ContextException {
        loadProperties(propertiesFilesName);
    }

    /**
     * Formatting symbols from reader and writes them to writer
     *
     * @param reader Reader to read symbols
     * @param writer Writer to write symbols
     * @throws ContextException if exception occurs
     */
    public void writeFormattedSymbol(IReader reader, IWriter writer) throws ContextException {
        try {
            while (reader.hasNext()) {
                char currentChar = reader.read();
                if (hasPreviousChar) {

                    writeAdditionalSpace(currentChar, writer);
                    writeNewLineSymbol(currentChar, writer);

                    if (Character.isWhitespace(currentChar) && previousChar == spaceSymbol) {
                        writer.write("");
                    } else {
                        writer.write(currentChar);
                        previousChar = currentChar;
                    }

                } else {
                    if (!Character.isWhitespace(currentChar)) {
                        writer.write(currentChar);
                        previousChar = currentChar;
                        hasPreviousChar = true;
                    }
                }
            }
            checkIsEnoughBrackets();
        } catch (ReaderException | WriterException e) {
            throw new ContextException(e);
        }
    }

    /**
     * Writes additional space symbols for special symbols:
     * left space for openParenthesis, openBrace
     * right space for closeParenthesis
     * both sides for specialSymbols or their combinations
     *
     * @param currentChar symbol to analise
     * @param writer      Writer to write to
     * @throws WriterException  if symbol cannot be written
     * @throws ContextException if parentheses balance is wrong
     */
    private void writeAdditionalSpace(char currentChar, IWriter writer) throws WriterException, ContextException {

        if (previousChar == closeParenthesis) {
            parenthesesLevel--;
            checkParenthesesBalance();
            if (currentChar != spaceSymbol && currentChar != dot && currentChar != openBrace) {
                writer.write(spaceSymbol);
            }
        }
        if (currentChar == openParenthesis) {
            parenthesesLevel++;
            if (previousChar != spaceSymbol) {
                writer.write(spaceSymbol);
            }
        }

        if (currentChar == openBrace && previousChar != spaceSymbol) {
            writer.write(spaceSymbol);
            previousChar = spaceSymbol;
        }
        if (specialSymbols.contains(previousChar) && !specialSymbols.contains(currentChar)) {
            writer.write(spaceSymbol);
            previousChar = spaceSymbol;
        }

        if (!specialSymbols.contains(previousChar) && specialSymbols.contains(currentChar)) {
            writer.write(spaceSymbol);
        }
    }

    /**
     * Writes newLine symbol after newLineSymbol, openBrace, openBrace
     * Increases offsetCounter after founding openBrace symbol
     * Decreases offsetCounter after founding closeBrace symbol
     *
     * @param currentChar symbol to analise
     * @param writer      Writer to write to
     * @throws WriterException  if symbol cannot be written
     * @throws ContextException if offset level is wrong
     */
    private void writeNewLineSymbol(char currentChar, IWriter writer) throws WriterException, ContextException {
        if (previousChar == openBrace) {
            offsetCount++;
            writer.write(newLineSymbol);
            previousChar = newLineSymbol;
        }
        if (currentChar == closeBrace) {
            offsetCount--;
        }
        if (previousChar == closeBrace) {
            writer.write(newLineSymbol);
            previousChar = newLineSymbol;
        }
        if (previousChar == endLineSymbol) {
            if (parenthesesLevel == 0) {
                writer.write(newLineSymbol);
                previousChar = newLineSymbol;
            }
        }
        if (previousChar == newLineSymbol) {
            writer.write(getOffset());
        }
    }

    /**
     * Calculates and returns current offset String
     *
     * @return current offset String
     * @throws ContextException if offset level is wrong
     */
    private String getOffset() throws ContextException {
        if (offsetCount < 0) {
            throw new ContextException("Wrong offset calculation!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offsetCount * offsetStep; i++) {
            sb.append(offsetSymbol);
        }
        return sb.toString();
    }

    /**
     * Checks parentheses balance
     *
     * @throws ContextException if parentheses balance is wrong
     */
    private void checkParenthesesBalance() throws ContextException {
        if (parenthesesLevel < 0) {
            throw new ContextException("Wrong parentheses balance!");
        }
    }

    /**
     * Checks all types of brackets balances
     *
     * @throws ContextException if brackets balance is wrong
     */
    private void checkIsEnoughBrackets() throws ContextException {
        if (parenthesesLevel > 0) {
            throw new ContextException("Not enough Parentheses!");
        }
        if (offsetCount > 0) {
            throw new ContextException("Not enough Braces!");
        }
    }


    /**
     * Loads format and context properties from given file
     *
     * @param propertiesFileName name of properties file
     * @throws ContextException if properties cannot be read
     */
    private void loadProperties(String propertiesFileName) throws ContextException {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("src/main/resources/java-format.properties")) {
            properties.load(is);
            offsetSymbol = properties.getProperty("offsetSymbol").charAt(1);
            offsetStep = Integer.parseInt(properties.getProperty("offsetStep"));
            offsetCount = Integer.parseInt(properties.getProperty("offsetCount"));

            openBrace = properties.getProperty("openBrace").charAt(0);
            closeBrace = properties.getProperty("closeBrace").charAt(0);

            openParenthesis = properties.getProperty("openParenthesis").charAt(0);
            closeParenthesis = properties.getProperty("closeParenthesis").charAt(0);

            newLineSymbol = properties.getProperty("newLineSymbol").charAt(1);
            spaceSymbol = properties.getProperty("spaceSymbol").charAt(1);
            endLineSymbol = properties.getProperty("endLineSymbol").charAt(0);

            for (String s : properties.getProperty("specialSymbols").split(",")) {
                specialSymbols.add(s.charAt(0));
            }

        } catch (IOException e) {
            throw new ContextException("Cannot load properties ", e);
        }
    }
}

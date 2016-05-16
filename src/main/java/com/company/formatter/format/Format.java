package com.company.formatter.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Format class loads and contains current formatter properties.
 */
public class Format implements IFormat {
    private List<Character> specialSymbols;
    private char offsetSymbol;
    private int offsetStep;

    /**
     * Constructs new Format file from default properties file.
     *
     * @throws FormatException if default properties file cannot be read
     */
    public Format() throws FormatException {
        String defaultPropertiesFile = "src/main/resources/java-format.properties";
        loadProperties(defaultPropertiesFile);
    }

    /**
     * Constructs new Format file from given properties file.
     *
     * @param propertiesFilesName properties file
     * @throws FormatException if given properties file cannot be read
     */
    public Format(final String propertiesFilesName) throws FormatException {
        loadProperties(propertiesFilesName);
    }

    /**
     * Loads Formatter properties from given file.
     *
     * @param propertiesFileName name of properties file
     * @throws FormatException if properties cannot be read
     */
    private void loadProperties(final String propertiesFileName) throws FormatException {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream(propertiesFileName)) {
            properties.load(is);
            offsetSymbol = properties.getProperty("offsetSymbol").charAt(1);
            offsetStep = Integer.parseInt(properties.getProperty("offsetStep"));

            specialSymbols = new ArrayList<>();
            for (String s : properties.getProperty("specialSymbols").split(",")) {
                specialSymbols.add(s.charAt(0));
            }

        } catch (IOException e) {
            throw new FormatException("Cannot load properties!", e);
        }
    }

    /**
     * Getter for special symbols which needs to be wrapped with space.
     *
     * @return List of Characters
     */
    public List<Character> getSpecialSymbols() {
        return specialSymbols;
    }

    /**
     * Getter for new line offset symbol.
     *
     * @return char offset symbol
     */
    public char getOffsetSymbol() {
        return offsetSymbol;
    }

    /**
     * Getter for new line offset step.
     *
     * @return offset step number
     */
    public int getOffsetStep() {
        return offsetStep;
    }
}

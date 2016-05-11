package aradionov.format;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class JavaFormat implements IFormat {
    List<String> operators = new ArrayList<>();

    private char offsetSymbol;
    private int offsetStep;
    private int offsetCount;
    private char offsetIncreaseSymbol;
    private char offsetDecreaseSymbol;
    private char roundBracketsLevel;
    private boolean checkRoundBrackets;
    private char newLineSymbol;
    private char spaceSymbol;
    private char endLineSymbol;

    public String getOffset() throws FormatException {
        if (offsetCount < 0) {
            throw new FormatException("Wrong offset calculation");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offsetCount * offsetStep; i++) {
            sb.append(offsetSymbol);
        }
        return sb.toString();
    }

    public List<String> getOperators() {
        return operators;
    }

    public void increaseOffset() {
        offsetCount++;
    }

    public void decreaseOffset() {
        offsetCount--;
    }

    public char getRoundBracketsLevel() {
        return roundBracketsLevel;
    }

    public void decreaseRoundBracketsLevel() {
        roundBracketsLevel--;
    }

    public void increaseRoundBracketsLevel() {
        roundBracketsLevel++;
    }

    public boolean isCheckRoundBrackets() {
        return checkRoundBrackets;
    }

    public char getOffsetIncreaseSymbol() {
        return offsetIncreaseSymbol;
    }

    public char getOffsetDecreaseSymbol() {
        return offsetDecreaseSymbol;
    }

    public char getNewLineSymbol() {
        return newLineSymbol;
    }

    public char getSpaceSymbol() {
        return spaceSymbol;
    }

    public char getEndLineSymbol() {
        return endLineSymbol;
    }

    public JavaFormat() {
        loadProperties();
    }

    private void loadProperties() {
        operators.add("if");
        operators.add("for");
        operators.add("while");

        offsetDecreaseSymbol = '}';
        offsetIncreaseSymbol = '{';

        offsetSymbol = ' ';
        offsetStep = 4;
        offsetCount = 0;
        offsetIncreaseSymbol = '{';
        offsetDecreaseSymbol = '}';
        roundBracketsLevel = 0;
        checkRoundBrackets = true;

        newLineSymbol = '\n';
        spaceSymbol = ' ';
        endLineSymbol = ';';
    }


}

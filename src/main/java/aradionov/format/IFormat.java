package aradionov.format;

import java.util.List;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface IFormat {
    List<String> getOperators();

    String getOffset() throws FormatException;

    void increaseOffset();

    void decreaseOffset();

    char getRoundBracketsLevel();

    void decreaseRoundBracketsLevel();

    void increaseRoundBracketsLevel();

    boolean isCheckRoundBrackets();

    char getOffsetIncreaseSymbol();

    char getOffsetDecreaseSymbol();

    char getNewLineSymbol();

    char getSpaceSymbol();

    char getEndLineSymbol();
}

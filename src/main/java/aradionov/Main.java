package aradionov;

import aradionov.format.ICommandExecutor;
import aradionov.format.IFormat;
import aradionov.format.JavaCommandExecutor;
import aradionov.format.JavaFormat;
import aradionov.formatter.Formatter;
import aradionov.formatter.FormatterException;
import aradionov.reader.FileReader;
import aradionov.reader.IReader;
import aradionov.reader.ReaderException;
import aradionov.reader.StringReader;
import aradionov.writer.FileWriter;
import aradionov.writer.IWriter;
import aradionov.writer.StringWriter;
import aradionov.writer.WriterException;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class Main {
    public static void main(String[] args) throws FormatterException, ReaderException, WriterException {
        IReader reader = null;
        IWriter writer = null;
        try {
            if ("-fr".equals(args[0])) {
                reader = new FileReader(args[1]);
            } else if ("-sr".equals(args[0])) {
                reader = new StringReader(args[1]);
            } else {
                return;
            }
            if ("-fw".equals(args[2])) {
                writer = new FileWriter(args[3]);
            } else if ("-sw".equals(args[2])) {
                writer = new StringWriter();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong parameters!");
            printHelp();
        }

        IFormat format = new JavaFormat();
        ICommandExecutor commandExecutor = new JavaCommandExecutor();
        Formatter formatter = new Formatter(format, commandExecutor);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
    }

    private static void printHelp() {
        System.out.println("First two parameters sets input: -fr fileName of -sr \"input string\"");
        System.out.println("Next parameter sets output: -fw fileName or sw (without 4th parameter)");
    }
}

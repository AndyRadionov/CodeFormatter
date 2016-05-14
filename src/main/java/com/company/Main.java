package com.company;

import com.company.formatter.Context;
import com.company.formatter.ContextException;
import com.company.formatter.Formatter;
import com.company.formatter.FormatterException;
import com.company.reader.FileReader;
import com.company.reader.IReader;
import com.company.reader.ReaderException;
import com.company.reader.StringReader;
import com.company.writer.FileWriter;
import com.company.writer.IWriter;
import com.company.writer.StringWriter;
import com.company.writer.WriterException;

/**
 * Main class for launching v2 formatter application
 *
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class Main {
    public static void main(String[] args) throws ReaderException, WriterException, ContextException, FormatterException {
        IReader reader = null;
        IWriter writer = null;
        try {
            if ("-fr".equals(args[0])) {
                reader = new FileReader(args[1]);
            } else if ("-sr".equals(args[0])) {
                reader = new StringReader(args[1]);
            } else {
                printHelp();
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
            return;
        }

        Context context = new Context();
        Formatter formatter = new Formatter(context);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
    }

    /**
     * Prints instructions for correct program using
     */
    private static void printHelp() {
        System.out.println("First two parameters sets input: -fr fileName of -sr \"input string\"");
        System.out.println("Next parameter sets output: -fw fileName or -sw (without 4th parameter)");
    }
}

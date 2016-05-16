package com.company.applauncher;

import com.company.formatter.Formatter;
import com.company.formatter.FormatterException;
import com.company.formatter.context.Context;
import com.company.formatter.context.IContext;
import com.company.formatter.format.Format;
import com.company.formatter.format.FormatException;
import com.company.formatter.format.IFormat;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateMachine;
import com.company.reader.FileReader;
import com.company.reader.IReader;
import com.company.reader.StringReader;
import com.company.writer.FileWriter;
import com.company.writer.IWriter;
import com.company.writer.StringWriter;

/**
 * Main class for launching v3 formatter application.
 *
 * @author Andrey Radionov andyomsk@gmail.com
 */
public final class Main {
    private Main() {
    }

    /**
     * Entry point for launching Formatter program.
     *
     * @param args contains input/output resources
     * @throws FormatException    if problem occurs while loading property file
     * @throws FormatterException if problem occurs while formatting symbols
     */
    public static void main(final String[] args) throws FormatException, FormatterException {
        IReader reader;
        IWriter writer;
        try {
            if ("-fr".equals(args[0])) {
                reader = new FileReader(args[2]);
            } else if ("-sr".equals(args[0])) {
                reader = new StringReader(args[2]);
            } else {
                throw new Exception();
            }
            if ("-fw".equals(args[1])) {
                writer = new FileWriter(args[2]);
            } else if ("-sw".equals(args[1])) {
                writer = new StringWriter();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Wrong parameters!");
            printHelp();
            return;
        }

        IFormat format = new Format();
        IContext context = new Context(format);
        IStateMachine stateMachine = new StateMachine(format);
        Formatter formatter = new Formatter(context, stateMachine);
        formatter.format(reader, writer);
        System.out.println(writer.getString());
    }

    /**
     * Prints instructions for correct program using
     */
    private static void printHelp() {
        System.out.println("-fr -fw inputFile outputFile");
        System.out.println("-sr -fw \"inputString\" outputFile");
        System.out.println("-fr -sw inputFile");
        System.out.println("-sr -sw \"inputString\"");
    }
}

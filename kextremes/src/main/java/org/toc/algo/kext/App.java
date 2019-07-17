package org.toc.algo.kext;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        configureLogger();
        KExtreme kmin = new KExtreme(5);
        int[] elements = { 16, 21, 43, 40, 15, 6, 75, 81, 9, 10 };
        show(kmin.getLeastElements(elements));
        show(kmin.getMostElements(elements));
    }

    private static void show(int[] extremes) {
        String s = Arrays.stream(extremes).mapToObj(String::valueOf).collect(Collectors.joining(","));

        logger.fine(s);
    }

    private static void configureLogger() {
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }
}

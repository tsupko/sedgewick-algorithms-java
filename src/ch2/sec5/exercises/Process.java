package ch2.sec5.exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author Alexander Tsupko (tsupko.alexander@yandex.ru)
 *         Copyright (c) 2016. All rights reserved.
 */
public class Process {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String FILE = DIRECTORY + "/src/ch2/sec5/exercises/sort.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))) {
            Stream<String> stringStream = bufferedReader.lines();
            DoubleStream doubleStream = stringStream.mapToDouble(Double::parseDouble);
            OptionalDouble optionalDouble = doubleStream.average();
            System.out.println(optionalDouble.isPresent() ? optionalDouble.getAsDouble() : optionalDouble.orElse(0.0));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

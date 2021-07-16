package testData;

import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataGenerator {

    public static void generatePeople() throws IOException {
        Faker faker = new Faker();
        Scanner sc = new Scanner(System.in);
        List<String[]> people = new ArrayList<>();

        System.out.print("How many people do you wish to generate?\n> ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.print("Type in the path to the csv\n> ");
        String csvPath = "test-data\\" + sc.nextLine();

        for (int i = 0; i < number; ++i) {
            String[] row = new String[4];
            row[0] = Integer.toString(i + 1);
            row[1] = faker.name().firstName();
            row[2] = faker.name().lastName();
            row[3] = faker.address().streetAddress();
            people.add(row);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvPath), ',',
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.DEFAULT_LINE_END)) {
            writer.writeAll(people);
            writer.flush();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
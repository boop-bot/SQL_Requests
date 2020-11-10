package by.kalugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String LOG_FILE_NAME = "src/data/logfile.txt";

    public static void main(String[] args) {

        try (Scanner inputCompaniesFile = new Scanner(new FileReader("src/data/input.csv"));
             Scanner inputRequestFile = new Scanner(new FileReader("src/data/requests.txt"))) {
            {
                LOGGER.setLevel(Level.FINE);
                FileHandler fh = new FileHandler(LOG_FILE_NAME, true);
                fh.setLevel(Level.FINE);
                fh.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fh);
                LOGGER.fine("Program started...\n");
            }
            List<Company> companyList = readListOfCompanies(inputCompaniesFile);
            String request;
            int fileCounter = 1;
            while (inputRequestFile.hasNextLine()){
                FileWriter file = new FileWriter(new File("src/data/request" + (fileCounter++) + ".txt"));
                request = inputRequestFile.nextLine().toLowerCase();
                processRequest(request, companyList, file);
                file.close();
            }
        }  catch (Exception exception) {
            LOGGER.fine("Error: " + exception);
        }
    }

    static List<Company> readListOfCompanies(Scanner input) throws ParseException {
        String[] args;
        List<Company> companyList = new ArrayList<>();

        while (input.hasNextLine()) {
            args = input.nextLine().split(";");
            companyList.add(new Company(args));
        }
        return companyList;
    }

    private static void writeFoundCompanies(FileWriter output, List<Company> list) throws IOException {
        if (list.isEmpty()) {
            output.write("NONE");
        }
        for (Company company : list) {
            output.write(company.toString() + System.lineSeparator());
        }
    }

    public static void processRequest(String request, List<Company> records, FileWriter file) throws IOException {
        String[] words = request.toLowerCase().split("(=[ ]*\".*\" )|(=[ ]*\".*\"$)|[ ]+");
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(request);
        if (words.length >= 4 && words[0].equals(SQLRequest.SELECT) && words[1].equals("*")
               && words[2].equals(SQLRequest.FROM)) {
            List<Company> result = records;

            if (words[3].equals(SQLRequest.TABLE_NAME)) {
                int i = 4;
                if (words[i].equals(SQLRequest.WHERE)) {
                    for (i = 5; i < words.length; ++i) {
                        switch (words[i]) {
                            case SQLRequest.SHORTNAME:
                                if (!matcher.find()) throw new IOException("Invalid command format. Wrong shortname command entered.");
                                result = Processor.findByShortName(result, request.substring(matcher.start() + 1, matcher.end() - 1));
                                break;
                            case SQLRequest.KIND_OF_ACTIVITY:
                                if (!matcher.find()) throw new IOException("Invalid command format. Wrong kind of activity command entered.");
                                result = Processor.findByActivity(result, request.substring(matcher.start() + 1, matcher.end() - 1));
                                break;
                            case SQLRequest.EMPLOYEE_NUMBER:
                                if (i + 4 < words.length && words[++i].equals(SQLRequest.BETWEEN) && words[i + 2].equals(SQLRequest.AND)) {
                                    int minNumber = Integer.parseInt(words[++i]);
                                    int maxNumber = Integer.parseInt(words[i + 2]);
                                    i = i + 2;
                                    result = Processor.findByEmployeeNumber(result, minNumber, maxNumber);
                                } else throw new IOException("Invalid command format. Wrong employee number command entered.");
                                break;
                            default:
                                throw new IOException("Invalid command format.");
                        }
                }
                LOGGER.fine("SQL request: " + request +
                        "\n\t\tCompanies found: " + result.size() + "\n ");
                writeFoundCompanies(file, result);
            } else throw new IOException("Invalid table name");
        } else {
            throw new IOException("Invalid command format");
        }
        }
    }
}
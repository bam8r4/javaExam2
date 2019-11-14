import java.util.*;
import java.io.*;

public class BabyNames {

    private static Map<Integer, List<String>> maleRank = new HashMap<Integer, List<String>>();
    private static Map<Integer, List<String>> femaleRank = new HashMap<Integer, List<String>>();

    public static void main(String[] args) {

        populateMaps();

        Scanner input = new Scanner(System.in);

        boolean keepSearching = true;


        do {
            System.out.println("Enter Year you would like to search for: ");
            int year = Integer.parseInt(input.next());

            System.out.println("Choose Male or Female (M or F): ");
            String gender = input.next();

            System.out.println("Enter the child's name:");
            String name = input.next();


            if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female"))
            {
                gender = "Female";
            }
            else if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
            {
                gender = "Male";
            }
            else
            {
                System.out.println("That is an incorrect input for the gender.");
            }

            List<String> namesForTheYear = null;
            if (gender.equals("Male"))
            {
                namesForTheYear = maleRank.get(year);
            }
            else
            {
                namesForTheYear = femaleRank.get(year);
            }

            if (namesForTheYear == null)
            {
                System.out.println("Details not found for the year " + year);
            }
            else
            {
                int rank = namesForTheYear.indexOf(name.toUpperCase()) + 1;

                if (rank == 0) {
                    System.out.println("Details Not Found!");
                } else {
                    System.out.println("The name "+name+" for the "+gender+" gender is ranked "+rank+" in the year "+year);

                }
            }

            System.out.println("Are you done searching for names? (Yes or No)\n");
            String response = input.next();

            if(response.equalsIgnoreCase("Yes"))
            {
                keepSearching = false;
            }

        } while(keepSearching);
    }

    private static void populateMaps()
    {
        String fileNameTemplate = "babynamesranking";

        int firstYear = 2001;
        final int lastYear = 2010;

        String nameOfFile = null;
        Scanner fileScanner = null;
        File file = null;
        while(firstYear <= lastYear){

            nameOfFile = fileNameTemplate+firstYear+".txt";

            //file = new File(nameOfFile);

            try {
                fileScanner = new Scanner(new File(nameOfFile));
            } catch (FileNotFoundException e) {

                System.out.println("Error: File not found for the year "+firstYear);
                return;
            }

            List<String> maleNames = new ArrayList<String>();
            List<String> femaleNames = new ArrayList<String>();
            while(fileScanner.hasNext()){

                String rank = fileScanner.next();

                String maleName = fileScanner.next();
                //fileScanner.next();

                String nameMaleAmount = fileScanner.next();

                String femaleName = fileScanner.next();
                //fileScanner.next();

                String nameFemaleAmount = fileScanner.next();

                maleNames.add(maleName.toUpperCase());
                femaleNames.add(femaleName.toUpperCase());
            }

            maleRank.put(firstYear, maleNames);
            femaleRank.put(firstYear, femaleNames);

            firstYear++;
        }
        fileScanner.close();//close file scanner
    }

    }


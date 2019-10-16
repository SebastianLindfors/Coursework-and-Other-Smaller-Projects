import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/** The main running class of the Storage System Project. This class contins the projects main()-method and and handles
 * saving data to disc and loading data from the disc. It also handles all the user input and output.
 * NOTE: It is very important when setting the system up to point the filePath variable to a suitable file
 */
public class Executable {

    /** The main executeable code of the projekt */
    public static void main(String[] args) {
        /** VERY IMPORTANT: Without this field being pointed to a valid file, the projekt does not work. */
       final String filePath = "C:\\Users\\sebas\\GitHub-Projects\\Coursework and Other Smaller Projects\\" +
                "Newton Objektsorienterad Programmering 1\\StorageTracking\\storagedata.txt";

       /** This variable keeps track of IDs that have been issued to created storables */
        ArrayList<Integer> usedIDs = new ArrayList<>();

        /** The mainDatabase will be the storage system that contains and manages all our data. */
        StorageSystem mainDatabase = null;

        //Try to load data from the filePath file. The file is empty an empty storage system will be created.
        try {mainDatabase = loadData(filePath); }
        catch(IOException e) {

            System.out.println("An Error occured during file handling");
            System.out.println(e.getMessage());
            System.exit(1);

        }
        //Main program loop here.
        while (true) {
            userInterface(mainDatabase, usedIDs, filePath);
        }

    }

    /** Method generates random ID-numbers for the storables to simulate them being tagged with a rfID tag.
     * It also checks that a new ID does not conflict with old IDs.
     * @param usedIds A list of ID-nymbers already in use.
     * @return A new unused ID-number as an Integer.*/
    private static Integer generateID(ArrayList<Integer> usedIds) {
        Integer newID = null;
        do {
            newID = (int) (Math.random() * 1000001);
        } while (usedIds.contains(newID));

        usedIds.add(newID);
        return newID;
    }

    /**Saves all data in the storage system to the file in filePath
     * @param storageSystem The Storage system to be saved.
     * @param fileName The path and name of the file in which data will be saved.*/
    private static void saveData(StorageSystem storageSystem, String fileName) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(fileName);
        String outputString =  "";
        for (Integer id : storageSystem.bookcaseHashMap.keySet()) {
            outputString += "BOOKCASEID: " + storageSystem.bookcaseHashMap.get(id).getIdNumber() + "\n";
            for (Storable storable : storageSystem.bookcaseHashMap.get(id).getListOfStoredStorables()) {
                if (storable != null) {
                outputString += storable.makeStorageString();
                }
            }
        }

        byte[] strToBytes = outputString.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

    /**Loads all data from the file into a new Storage System
     * @param fileName The path and name of the file from which data will be read*/
    private static StorageSystem loadData(String fileName) throws IOException {

        ArrayList<Bookcase> listOfBookcases = new ArrayList<>();
        ArrayList<Storable> checkedOutItems = new ArrayList<>();


        try (BufferedReader inputReader = new BufferedReader(new FileReader(fileName))) {
            String line = inputReader.readLine();

            String[] lineParts;
            Integer bcID = -1;

            Bookcase currentBookcase = null;

            while (line != null) {
                System.out.println(line);
                lineParts = line.split("\\|");
                for (String s : lineParts) System.out.println(s);
                if (lineParts.length == 1) {
                    String[] getNumberSplit = line.split(" ");
                    bcID = Integer.parseInt(getNumberSplit[1]);
                    currentBookcase = new Bookcase(bcID);
                    listOfBookcases.add(currentBookcase);
                }
                else {
                    currentBookcase.StoreStorable(createStorableFromArray(lineParts));
                }
                line = inputReader.readLine();
            }


        }
        return new StorageSystem(listOfBookcases, checkedOutItems);
    }

    /** Helper method for the loadData() method. This method creates storables of different types based on data read from the file.
     *
     * @param attributes An array of attributes to be constructed into a storable object.
     * @param <S> A generic return type that is a subclass of Storable.
     * @return Returns a storable
     */
    private static <S extends Storable> S createStorableFromArray(String[] attributes) {
        S output;
        if (attributes[4].equals("Book")) {
            Book newBook = new Book(attributes[0],Integer.parseInt(attributes[1]),Integer.parseInt(attributes[2]));
            newBook.setTags(attributes[3].split(","));
            newBook.setAuthor(attributes[5]);
            newBook.setSeries(attributes[6]);
            newBook.setGenre(attributes[7]);
            output = (S) newBook;
        }
        else if (attributes[4].equals("Game")) {
            Game newGame = new Game(attributes[0],Integer.parseInt(attributes[1]),Integer.parseInt(attributes[2]));
            newGame.setTags(attributes[3].split(","));
            newGame.setPlatform(attributes[5]);
            newGame.setGenre(attributes[6]);
            output = (S) newGame;
        }
        else {
            Movie newMovie = new Movie(attributes[0],Integer.parseInt(attributes[1]),Integer.parseInt(attributes[2]));
            newMovie.setTags(attributes[3].split(","));
            newMovie.setYear(attributes[5]);
            newMovie.setGenre(attributes[6]);
            output = (S) newMovie;
        }

        return output;
    }

    /** Method for performing a text matching search where you match a short string to a longer one.
     * Used in this program to allow you to search for titles or genres or other fields of data saved.
     *
     * Uses the Knuth-Pratt-Morris text matching algorithm.
     *
     * @param pattern The pattern you wish to search for
     * @param database The String you wish to search through.
     * @return True if the pattern matches anywhere in the string, false otherwise.
     */
    private static boolean KMPSearch(String pattern, String database) {
        int patternLength = pattern.length();
        int databaseLength = database.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[patternLength];
        int j = 0; // index for pattern[]

        // Calculate lps[] array
        computeLPSArray(pattern, patternLength, lps);

        int i = 0; // index for database[]
        while (i < databaseLength) {
            if (pattern.charAt(j) == database.charAt(i)) {
                j++;
                i++;
            }
            if (j == patternLength) {
                return true;
            }

            // mismatch after j matches
            else if (i < databaseLength && pattern.charAt(j) != database.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return false;
    }

    /** Helper function to the text search algortihm, creates the auxileery
     *  array used to spped up the search. See KMP alrgorithm for more info.
     */
    static void computeLPSArray(String pattern, int M, int lps[]) {

        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];

                }
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }


    /** This method searches the provided database for any storables which
     * data fields contain any match to the provided pattern.
     * It returns all matching storables in an ArrayList
     *
     * The filter parameter allows you to filter on type, attribute or bookcase.
     *
      * @param database The Database to be searched.
     * @param text  The text you are searching for
     * @param filter  An Array of Arrays of Integers, which are passed to other filter functions to filter out which fields are searched
     * @return An ArrayLIst of all sotrables in the database which matches the text search.
     */
    static ArrayList<Storable> textSearch(StorageSystem database, String text, Integer[][] filter) {

        ArrayList<Bookcase> searchBookcaseSpace =  database.getBookcaseSubset(filter[2]);
        ArrayList<Storable> storableToSearchList = new ArrayList<>();
        ArrayList<Storable> searchResults = new ArrayList<>();

        for (Bookcase bookcase : searchBookcaseSpace) {
            storableToSearchList.addAll(bookcase.getSubsetOfStorables(filter[0]));
        }
        for (Storable storable : storableToSearchList) {
            if (storable != null) {
                if (KMPSearch(text, storable.makeSearchString(filter[1]))) searchResults.add(storable);
            }
        }
        return searchResults;
    }

    /** A small helper method that asks for user inputs and ony accepts a predetermined range of responses
     * The methods repeats the question until it gets a proper response.
     * The responses should be given as numbered alternatives.
     *
     * @param inputScanner The scanner used to gather the input.
     * @param queryString   The string that asks the user for input
     * @param acceptedResponses List of accepted answers, if {} any answer is accepted.
     * @return  The answer the user provided, as an Integer.
     */
    public static int queryUser(Scanner inputScanner, String queryString, Integer[] acceptedResponses) {
        while(true) {
            System.out.print(queryString);
            try {
                int output = inputScanner.nextInt();

                if (acceptedResponses.length == 0) return output;

                for (int i : acceptedResponses) {
                    if (output == i) return i;
                }
            }
            catch (InputMismatchException e) {
                inputScanner.next();
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    /** The main user interface method. Basically a bunch of user input
     * prompts that trigger the various functions of this program
     *
     * @param mainDatabase The Storage System we are working with.
     * @param usedIDs   The used ID-numbers in that ssytem.
     * @param filePath  The file path of the system for the purpose of saving and loading.
     */
    private static void userInterface(StorageSystem mainDatabase, ArrayList<Integer> usedIDs, String filePath) {

        String mainMenuString = "What would you like to do?" + "\n" +
                "1: Search for item." + "\n" +
                "2: Add new item to system." + "\n" +
                "3: Check out item from bookcase." + "\n" +
                "4: Check in item into a bookcase." + "\n" +
                "5: Save and Quit" + "\n";
        Integer[] mainMenuChoices = {1,2,3,4,5};

        String searchMenuString = "Do you wish to perform an universal or filtered search?" + "\n" +
                "1: Universal Search." + "\n" +
                "2: Filtered Search." + "\n" ;
        Integer[] searchMenuChoices = {1,2};

        String typeFilterString = "Do you wish to filter by item type?" + "\n" +
                "1: No, do not filter by type." + "\n" +
                "2: Yes, show only Book results." + "\n" +
                "3: Yes, show only Movie results." + "\n" +
                "4: Yes, show only Game results." + "\n";
        Integer[] typeFilterChoices = {1,2,3,4};

        String attributeFilterString = "Do you wish to filter by attribute" + "\n" +
                "Item Type: \t Book \t Movie \t Game" + "\n" +
                "1: \t No filter \t No Filter \t NoFilter" + "\n" +
                "2: \t Title \t Title \t Title" + "\n" +
                "3: \t ID-Number \t ID-Number \t ID-number" + "\n" +
                "4: \t Home Bookcase \t Home Bookcase \t Home Bookcase \t " + "\n" +
                "5: \t Tags \t Tags \t Tags" + "\n" +
                "6: \t Author \t Year of Realese \t Platform" + "\n" +
                "7: \t Genre \t Genre \t Genre" + "\n" +
                "8: \t Series" + "\n";
        Integer[] attributeFilterChoices = {1,2,3,4,5,6,7,8};

        String bookcaseFilterString = "Do you wish to filter on storage loaction?" + "\n" +
                "(To filter on checked out items, please enter 0)" + "\n" +
                "1: Do not filter on storage location" + "\n";

        int j = 2;
        for (Integer i : mainDatabase.bookcaseHashMap.keySet()) {
            if (i == -1) continue;
            bookcaseFilterString += j + ": BoocKase " + i + "\n";
            j++;
        }

        Integer[] bookcaseFilterChoices = new Integer[j+1];
        for (Integer i = 0; i < j + 1; i++) {
            bookcaseFilterChoices[i] = i;
        }

        String newItemString = "What type of object is being added?" + "\n" +
                "1: Book" + "\n" +
                "2: Movie" + "\n" +
                "3: Game" + "\n";
        Integer[] newItemChoices = {1,2,3};

        int itemID;
        int bookcaseID;

        Scanner inputScanner = new Scanner(System.in);
        int userChoice = -1;

        System.out.println("Welcome to the Bookcase Interactive Storage System.");
        System.out.println("There are currently " + mainDatabase.totalNumberOfStoredStorables + " items stored across " +
                               + (mainDatabase.bookcaseHashMap.size() - 1) + " bookcases" );

        userChoice = queryUser(inputScanner, mainMenuString, mainMenuChoices);
        switch(userChoice) {
            case 1: //-------------------------------------------------------------------------------------------//
                System.out.println("Item searching selected:");

                ArrayList<Storable> results;

                Integer[] typeFilter = {0};
                Integer[] attributeFilter = {0};
                Integer[] bookcaseFilter = {0};
                Integer[][] searchFilter = {typeFilter, attributeFilter, bookcaseFilter};

                if (queryUser(inputScanner, searchMenuString, searchMenuChoices) == 2) {
                    System.out.println("Please select filter criteria:");
                    userChoice = queryUser(inputScanner, typeFilterString, typeFilterChoices);
                    typeFilter = new Integer[] {userChoice - 1};
                    userChoice = queryUser(inputScanner, attributeFilterString, attributeFilterChoices);
                    attributeFilter = new Integer[] {userChoice - 1};
                    userChoice = queryUser(inputScanner, bookcaseFilterString, bookcaseFilterChoices);
                    bookcaseFilter = new Integer[] {userChoice - 1};

                    searchFilter = new Integer[][] {typeFilter, attributeFilter, bookcaseFilter};


                }
                System.out.println("Please enter search phrase:");
                inputScanner.nextLine();
                String searchPhrase = inputScanner.nextLine();

                results = textSearch(mainDatabase, searchPhrase, searchFilter);

                for (Storable storable : results) {
                    System.out.println(storable.makeStorageString());
                }

                break;
            case 2: //-------------------------------------------------------------------------------------------//
                String[] attributes = new String[8];
                System.out.println("Creating new item parameters.");
                String newTag = "#NOTAG#";
                String tags = "";
                int type = queryUser(inputScanner, newItemString, newItemChoices);
                inputScanner.nextLine();
                switch(type) {
                    case 1:
                        attributes[4] = "Book";
                        System.out.println("What is the title of the book?");
                        attributes[0] = inputScanner.nextLine();
                        System.out.println("Who is the books author?");
                        attributes[5] = inputScanner.nextLine();
                        System.out.println("What series is the book part of? (Leave blank if book is a stand alone work)");
                        attributes[6] = inputScanner.nextLine();
                        System.out.println("What genre is the book?");
                        attributes[7] = inputScanner.nextLine();
                        System.out.println("In which bookcase does the book belong?");
                        attributes[2] = inputScanner.nextLine();
                        System.out.println("Finally, do you wish to add any tags to the book? " +
                                "(You may enter more than one, leave blank when done.");

                        while (!newTag.equals("")) {
                            if (!newTag.equals("#NOTAG#")) {
                                tags += newTag +",";
                            }
                            newTag = inputScanner.nextLine();
                        }
                        attributes[3] = tags;

                        attributes[1] = generateID(usedIDs).toString();

                        System.out.println(createStorableFromArray(attributes).makeStorageString());
                        break;
                    case 2:
                        attributes[4] = "Movie";
                        System.out.println("What is the title of the movie?");
                        attributes[0] = inputScanner.nextLine();
                        System.out.println("What year did the movie come out");
                        attributes[5] = inputScanner.nextLine();
                        System.out.println("What genre is the movie?");
                        attributes[7] = inputScanner.nextLine();
                        System.out.println("In which bookcase does the movie belong?");
                        attributes[2] = inputScanner.nextLine();
                        System.out.println("Finally, do you wish to add any tags to the movie? " +
                                "(You may enter more than one, leave blank when done.");

                        while (!newTag.equals("")) {
                            if (!newTag.equals("#NOTAG#")) {
                                tags += newTag +",";
                            }
                            newTag = inputScanner.nextLine();
                        }
                        attributes[3] = tags;

                        attributes[1] = generateID(usedIDs).toString();

                        System.out.println(createStorableFromArray(attributes).makeStorageString());
                        break;
                    case 3:
                        attributes[4] = "Game";
                        System.out.println("What is the title of the game?");
                        attributes[0] = inputScanner.nextLine();
                        System.out.println("For which platform is the game?");
                        attributes[5] = inputScanner.nextLine();
                        System.out.println("What genre is the movie?");
                        attributes[7] = inputScanner.nextLine();
                        System.out.println("In which bookcase does the book belong?");
                        attributes[2] = inputScanner.nextLine();
                        System.out.println("Finally, do you wish to add any tags to the game? " +
                                "(You may enter more than one, leave blank when done.");

                        while (!newTag.equals("")) {
                            if (!newTag.equals("#NOTAG#")) {
                                tags += newTag +",";
                            }
                            newTag = inputScanner.nextLine();
                        }
                        attributes[3] = tags;

                        attributes[1] = generateID(usedIDs).toString();

                        System.out.println(createStorableFromArray(attributes).makeStorageString());
                        break;
                }
                Storable newStorable = createStorableFromArray(attributes);
                mainDatabase.putStorableInSystem(newStorable);
                mainDatabase.checkInStorable(newStorable.getRfidNumber(), newStorable.getCorrectStoragePlace());
                System.out.println("New item " + newStorable.getName() + " stored in bookcase " + newStorable.getCorrectStoragePlace());
                break;
            case 3: //-------------------------------------------------------------------------------------------//
                System.out.println("Please enter item ID:");
                itemID = inputScanner.nextInt();
                bookcaseID = mainDatabase.checkOutStorable(itemID);
                if (bookcaseID == 0) {
                    System.out.println("Sorry, could not locate item with ID: " + itemID);
                    System.out.println("Please try another ID-number");
                    break;
                }
                else {
                    System.out.println("Item: " + mainDatabase.bookcaseHashMap.get(-1).getStorableFromID(itemID).getName() +
                            " taken out of bookcase " + bookcaseID);
                }
                break;
            case 4: //-------------------------------------------------------------------------------------------//
                System.out.println("Please enter item ID:");
                itemID = inputScanner.nextInt();
                Storable storable = mainDatabase.bookcaseHashMap.get(-1).getStorableFromID(itemID);
                if (storable == null) {
                    System.out.println("Item not found among cheked out items, please try a different ID-number");
                    break;
                }
                String bookcaseStorageString = "In which bookcase do you wish to store " + storable.getName() + "?\n";
                int k = 1;
                for (Integer i : mainDatabase.bookcaseHashMap.keySet()) {
                    if (i == -1) continue;
                    bookcaseStorageString += k + ": BoocKase " + i + "\n";
                    k++;
                }
                Integer[] bookcaseStorageChoices = new Integer[j];
                for (Integer i = 0; i < j - 1; i++) {
                    bookcaseStorageChoices[i] = i + 1;
                }
                bookcaseID = queryUser(inputScanner, bookcaseStorageString, bookcaseStorageChoices);
                mainDatabase.checkInStorable(storable.getRfidNumber(), bookcaseID);
                System.out.println("Item stored in bookcase " + bookcaseID);
                break;
            case 5: //-------------------------------------------------------------------------------------//
                try {
                    saveData(mainDatabase, filePath);
                    System.exit(0);
                }
                catch (IOException e) {
                    System.out.println("Failed to save system on exit, data lozs may have occurred");
                    e.printStackTrace();
                    System.exit(1);
                }


        }

    }

}

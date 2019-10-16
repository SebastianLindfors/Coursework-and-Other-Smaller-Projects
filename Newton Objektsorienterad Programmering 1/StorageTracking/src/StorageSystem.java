import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/** This class is used to keep track of and order the different bookcases used in the storage system.
 *  It uses a HashMap to keep each bookcase hashed to its ID-number. It uses the reserved ID-number -1
 *  for a bookcase that represents items checked out of any bookcase but still in the system.
 *
 */
public class StorageSystem {

    // ---- Private Variables ---- //
/** This variables keeps track of the total number of items stored in the system. */
    Integer totalNumberOfStoredStorables;

    /** This is the hashMap taht keeps track of the bookcases */
    Map<Integer, Bookcase> bookcaseHashMap;

    // ---- Constructors ---- //

    /** This constructor constructs a new storage system from an array of bookcases and has no cheked out items.
     * As stated above, bookcases may NOT have ID-number 0 or -1. */
    public StorageSystem(ArrayList<Bookcase> list) {

        totalNumberOfStoredStorables = 0;
        bookcaseHashMap = new HashMap<>();
        bookcaseHashMap.put(-1, new Bookcase(-1));

        for (Bookcase bc : list) {
            Integer id = bc.getIdNumber();
            if (id != 0 && id != -1) {
                bookcaseHashMap.put(id, bc);
                totalNumberOfStoredStorables += bc.getNumberOfStoredStorables();
            }
            else {
                throw new IllegalArgumentException("Bookcases with ID = 0 or ID = -1 are reserved for program functions");
            }
        }

    }

    /** This constructor constructs a new storage system from an array of bookcases and
     *  takes an additional ArrayList of storables as the list of checked out items.
     *  These items are checked into bookcase -1.
     * As stated above, bookcases may NOT have ID-number 0 or -1. */
    public StorageSystem(ArrayList<Bookcase> list, ArrayList<Storable> checkedOut) {

        totalNumberOfStoredStorables = 0;
        bookcaseHashMap = new HashMap<>();

        for (Bookcase bc : list) {
            Integer id = bc.getIdNumber();
            bookcaseHashMap.put(id, bc);
            totalNumberOfStoredStorables += bc.getNumberOfStoredStorables();
        }
        for (Storable storable : checkedOut) {
            bookcaseHashMap.get(-1).StoreStorable(storable);
            totalNumberOfStoredStorables++;
        }

    }

    // ---- Methods ---- //

    /** Adds a new storable to the system */
    public void putStorableInSystem(Storable thing) {

        if (isStorableInSystem(thing.getRfidNumber())) {
            System.out.println("Error: ID-number already exists in system.");
        }
        else {
            bookcaseHashMap.get(-1).StoreStorable(thing);
            totalNumberOfStoredStorables++;
        }

    }

    /** This method moves and item from the checked out bookcase to another bookcase
     * @param storableID The ID of the item you wish to check in.
     * @param bookcaseID The Id of the bookcase to check the items in to.
     * */
    public void checkInStorable(Integer storableID, Integer bookcaseID) {
        //Verify that the bookcase exists in the system.
        Bookcase bookcase = null;
        for (Integer id : bookcaseHashMap.keySet()) {
            if (bookcaseID == id) {
                bookcase = bookcaseHashMap.get(id);
                break;
            }
        }
        if (bookcase == null) {
            System.out.println("Cannot bookcase ID-number " + bookcaseID + "not in system.");
            return; //Exit method because of bookcase error.
        }

        //Find the item among the checked out storables
        for (Storable storable : bookcaseHashMap.get(-1).getListOfStoredStorables()) {
            if (storableID == storable.getRfidNumber()) {
                bookcase.StoreStorable(storable);
                bookcaseHashMap.get(-1).takeOutStorable(storable);

                return; //Exit function successfully.
            }
        }
        if (!isStorableInSystem(storableID)) {
            System.out.println("Storable ID not found in sytem.");
            return;
        }


    }

/** This method moves and item to the checked out bookcase (-1)
 * @param storableID The ID of the item you wish to check out. */
    public int checkOutStorable(Integer storableID) {
        for (Integer i : bookcaseHashMap.keySet()) {
            for (Storable storable : bookcaseHashMap.get(i).getListOfStoredStorables()) {
                if (storable != null) {
                    if (storable.getRfidNumber() == storableID) {
                        bookcaseHashMap.get(i).takeOutStorable(storable);
                        bookcaseHashMap.get(-1).StoreStorable(storable);
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    /** Checks if a storable with the given ID-number is in the system.
     * @param storableID The ID-number of the storable you are searching for.
     * @return  A boolean, True if the ID-number was found. Otherwise False */
    public boolean isStorableInSystem(Integer storableID) {

        Boolean foundInSystem = false;

        for (Integer key : bookcaseHashMap.keySet()) {
            Bookcase currentBookcase = bookcaseHashMap.get(key);
            for (Storable s : currentBookcase) {
                if (s.getRfidNumber() == storableID) {
                    foundInSystem = true;
                    System.out.println("ID number matched in bookcase with id: " + currentBookcase.getIdNumber());
                    break;
                }
            }
        }

        return foundInSystem;
    }

    /** This method is used in the text search to filter on specific bookcases
     * @param list A list of the Bookcases ID-numbers to be included in the search
     * @return A subset consisting of the requested bokcases.*/
    public ArrayList<Bookcase> getBookcaseSubset(Integer[] list) {

        if (list.length == 1 && list[0] == 0) {
            list =  bookcaseHashMap.keySet().toArray(new Integer [bookcaseHashMap.keySet().size()]);
        }

        ArrayList<Bookcase> output = new ArrayList<Bookcase>();
        for (int i : list) {
            if (bookcaseHashMap.containsKey(i)) {
                output.add(bookcaseHashMap.get(i));
            }
            else {
                throw new IllegalArgumentException("Bookcase with ID-number " + i + "not found in system");
            }
        }

        return output;
    }
}

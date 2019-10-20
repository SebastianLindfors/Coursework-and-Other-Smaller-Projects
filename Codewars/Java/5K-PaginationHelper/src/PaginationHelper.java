import java.util.List;

/** This class solves the Codewars Kata: Pagination Helper
 *  https://www.codewars.com/kata/515bb423de843ea99400000a
 *
 *  Author: Sebastian Lindfors
 */
public class PaginationHelper<I> {

    private int numberOfStoredItems;
    private int numberOfPages;
    private int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {

        this.itemsPerPage = itemsPerPage;

        numberOfStoredItems = collection.size();
        numberOfPages = collection.size()/itemsPerPage + 1;


    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return numberOfStoredItems;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return numberOfPages;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {

        if (pageIndex < 0 || pageIndex > numberOfPages) return -1;

        if (pageIndex == numberOfPages - 1) return numberOfStoredItems % this.itemsPerPage;
        else return this.itemsPerPage;

    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {

        if (itemIndex < 0 || itemIndex > numberOfStoredItems) return -1;
        if (numberOfStoredItems == 0) return -1;
        else return itemIndex/this.itemsPerPage;


    }

}

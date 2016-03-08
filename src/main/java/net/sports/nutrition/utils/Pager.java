package net.sports.nutrition.utils;

import org.springframework.beans.support.PagedListHolder;

/**
 * This class creates information for pagination from PageListHolder.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class Pager implements java.io.Serializable {

    private int beginIndex;
    private int endIndex;
    private int currentIndex;
    private int totalPageCount;
    private String baseUrl;
    private int totalItems;

    /**
     * Returns new Page instance width pagination information.
     *
     * @param pagedListHolder - a simple state holder for handling lists of objects
     * @param BASE_URL        - url for build pagination
     * @return Pager object
     */
    public static Pager currentPage(PagedListHolder<?> pagedListHolder, String BASE_URL) {
        int currentIndex = pagedListHolder.getPage() + 1;
        int beginIndex = Math.max(1, currentIndex - 2);
        int endIndex = Math.min(beginIndex + 4, pagedListHolder.getPageCount());
        int totalPageCount = pagedListHolder.getPageCount();
        int totalItems = pagedListHolder.getNrOfElements();
        String baseUrl = BASE_URL;

        Pager pager = new Pager();
        pager.setBeginIndex(beginIndex);
        pager.setEndIndex(endIndex);
        pager.setCurrentIndex(currentIndex);
        pager.setTotalPageCount(totalPageCount);
        pager.setTotalItems(totalItems);
        pager.setBaseUrl(baseUrl);
        return pager;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

}


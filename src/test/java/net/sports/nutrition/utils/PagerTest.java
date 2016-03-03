package net.sports.nutrition.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.support.PagedListHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 11:37
 */

public class PagerTest {

    private String baseUrl;
    private PagedListHolder pageListHolder;

    @Before
    public void init() {
        baseUrl = "/some/url";
        pageListHolder = Mockito.mock(PagedListHolder.class);
        when(pageListHolder.getPage()).thenReturn(2);
        when(pageListHolder.getPageCount()).thenReturn(10);
        when(pageListHolder.getNrOfElements()).thenReturn(50);
    }

    @Test
    public void testCurrentPage() {
        Pager pager = Pager.currentPage(pageListHolder, baseUrl);
        assertNotNull(pager);
        assertEquals(baseUrl, pager.getBaseUrl());
        assertEquals(3, pager.getCurrentIndex());
        assertEquals(1, pager.getBeginIndex());
        assertEquals(5, pager.getEndIndex());
        assertEquals(10, pager.getTotalPageCount());
        assertEquals(50, pager.getTotalItems());
    }
}
package br.com.leucotron.livre.core.dto;

import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO for filter the search.
 *
 * @author Virtus
 */
public class SearchFilterDTO {

    /**
     * Current page.
     */
    private Integer currentPage = 1;

    /**
     * Page size.
     */
    private Integer pageSize = 10;

    /**
     * Sort Page (ASC, DESC)
     */
    private String sort = "";

    /**
     * Column to sorting.
     */
    private String column = "";

    /**
     * Filters.
     */
    private Map<String, Object> filters = new HashMap<>();

    /**
     * Gets the current page.
     *
     * @return Current page.
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets the current page.
     *
     * @param currentPage Current page.
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets the page size.
     *
     * @return Page size.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize Page size.
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get the filters.
     *
     * @return Filters.
     */
    public Map<String, Object> getFilters() {
        return filters;
    }

    /**
     * Sets the filters.
     *
     * @param filters Filters.
     */
    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    /**
     * Get the Sort
     *
     * @return
     */
    public String getSort() {
        return sort;
    }

    /**
     * Set Sort method.
     *
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Get Column sort.
     * @return
     */
    public String getColumn() {
        return column;
    }

    /**
     * Set Column sort.
     * @param column
     */
    public void setColumn(String column) {
        this.column = column;
    }
}

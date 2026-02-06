package com.logicoy.api.productsearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//Contains pagination details for search results.

@Schema(description = "Pagination metadata")
public class PaginationMeta {

   
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

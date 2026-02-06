package com.logicoy.api.productsearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

// Standard search response wrapper,Keeps response structure consistent.
 
@Schema(description = "Search response")
public class SearchResponse {

    private List<ProductDto> data;
    private PaginationMeta meta;

    public List<ProductDto> getData() {
        return data;
    }

    public void setData(List<ProductDto> data) {
        this.data = data;
    }

    public PaginationMeta getMeta() {
        return meta;
    }

    public void setMeta(PaginationMeta meta) {
        this.meta = meta;
    }
}

package com.shark.application.dto;

import java.util.List;

public class PageDtoEntity<Data> {

    private long totalElements;

    private long totalPages;

    private List<Data> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<Data> getContent() {
        return content;
    }

    public void setContent(List<Data> content) {
        this.content = content;
    }
}

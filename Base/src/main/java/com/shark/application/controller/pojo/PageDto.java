package com.shark.application.controller.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<Content> {

    private long totalElements;

    private long totalPages;

    private List<Content> contentList;
}

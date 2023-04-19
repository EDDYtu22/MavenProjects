package dev.edmond.springstart.web.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
public class PersonApiPage<T> {

    private List<T> contenet;
    private PaginationMetaData pagination;

    public PersonApiPage(Page<T> springPage) {
        this.contenet = springPage.getContent();
        this.pagination = PaginationMetaData.builder()
                .currentPage(springPage.getPageable().getPageNumber())
                .totalElements(springPage.getTotalElements())
                .totalPages(springPage.getTotalPages())
                .build();
    }

    @Data
    @Builder
    private static class PaginationMetaData {
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
    }

}

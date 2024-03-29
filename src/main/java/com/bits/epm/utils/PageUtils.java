package com.bits.epm.utils;

import com.bits.epm.data.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class PageUtils {

    public static <T> PageDTO<T> build(List<T> content, Pageable pageable, long total) {
        return build(new PageImpl<>(content, pageable, total));
    }

    public static <T> PageDTO<T> build(List<T> content) {
        return build(new PageImpl<>(content));
    }

    public static <T> PageDTO<T> build(Page<T> page) {
        return PageDTO.<T>builder()
                .content(page.getContent())
                .numberOfElements(page.getNumberOfElements())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }


}

package com.bits.epm.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO<T> implements Serializable {
    List<T> content;
    int numberOfElements;
    int page;
    int size;
    long totalElements;
    int totalPages;
}

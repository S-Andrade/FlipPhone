package com.ua.flipPhone.product;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface Resource<T> {
    @GetMapping("/search/{searchText}")
    ResponseEntity<Page<T>> findAll(Pageable pageable, @PathVariable String searchText);

    @GetMapping
    ResponseEntity<Page<T>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir);

}

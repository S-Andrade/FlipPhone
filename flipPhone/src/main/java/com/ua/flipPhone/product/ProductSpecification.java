
package com.ua.flipPhone.product;

import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product>{
    
    private List<SearchCriteria> list;

    public ProductSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }
    
     @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

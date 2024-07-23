package com.junior.eshop.repository;

import com.junior.eshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where name = :name", nativeQuery = true)
    Category findCategoryByName(String name);
}

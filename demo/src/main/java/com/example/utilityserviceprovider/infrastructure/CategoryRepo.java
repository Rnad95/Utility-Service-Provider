package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category , Long> {
    Category findCategoriesByTitle(String title);

//    @Query("select c from Category c where c.parent is null")
//    List<Category> findMainCategories();

}

package com.YunussEmree.buyer.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String category);

    boolean existByName(String Name);
}

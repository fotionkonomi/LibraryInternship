package org.lms.dao;

import java.util.List;

import org.lms.dto.CategoryDTO;
import org.lms.model.Category;
 
public interface CategoryDAO {
 
    public void addCategory(CategoryDTO category);
    void updateCategory(CategoryDTO category);
    public List<CategoryDTO> listCategory();
    CategoryDTO categoryViaCategory(String categoryName);
    Category getCategoryById(CategoryDTO categoryDTO);
}
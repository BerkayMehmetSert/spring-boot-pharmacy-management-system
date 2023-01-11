package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.CategoryDto;
import com.bms.pharmacymanagementsystem.dto.converter.CategoryDtoConverter;
import com.bms.pharmacymanagementsystem.exception.CategoryAlreadyExistException;
import com.bms.pharmacymanagementsystem.exception.CategoryListEmptyException;
import com.bms.pharmacymanagementsystem.exception.CategoryNotFoundException;
import com.bms.pharmacymanagementsystem.model.Category;
import com.bms.pharmacymanagementsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public void createCategory(final String name) {
        checkIfCategoryExists(name);
        categoryRepository.save(new Category(name));
    }

    public void updateCategory(final String id, final String name) {
        Category category = getCategoryById(id);

        if (!category.getName().equals(name)) {
            checkIfCategoryExists(name);
        }

        Category updatedCategory = new Category(
                category.getId(),
                name,
                category.getDate(),
                category.getMedicines()
        );

        categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(final String id) {
        categoryRepository.delete(getCategoryById(id));
    }

    public CategoryDto findCategoryById(final String id) {
        return converter.convert(getCategoryById(id));
    }

    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()){
            throw new CategoryListEmptyException("Category list is empty");
        }

        return converter.convert(categories);
    }

    protected Category getCategoryById(final String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found by id: " + id));
    }

    private void checkIfCategoryExists(final String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new CategoryAlreadyExistException("Category already exist by name: " + name);
        }
    }
}

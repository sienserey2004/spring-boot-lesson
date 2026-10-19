package com.setec.appcoffeeshop.features.categories;

import com.setec.appcoffeeshop.exceptions.MyResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        var categories = this.categoryRepository.findAll();
        return categories;
    }

    @Transactional
    public void create(Category category) {
        this.categoryRepository.save(category);
    }

    public Category findById(int id) {
        var categoryExist = this.categoryRepository.findCategoryById(id);
        if (categoryExist == null) {
            throw new MyResourceNotFoundException("Category ", "id", id);
        }
        return categoryExist;
    }

    @Transactional
    public Category updateById(int id, Category category) {
        var categoryExist = this.findById(id);

        categoryExist.setName(category.getName());
        categoryExist.setStatus(category.isStatus());

        this.categoryRepository.save(categoryExist);
        return categoryExist;
    }

    @Transactional
    public Category deleteById(int id) {
        var categoryExist = this.findById(id);
        this.categoryRepository.delete(categoryExist);

        return categoryExist;
    }

    public Page<Category> paginated(int pageNo, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNo,  pageSize);

        if(keyword != null && !keyword.isBlank()) {
            return categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

        return categoryRepository.findAll(pageable);
    }
}

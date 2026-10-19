package com.setec.appcoffeeshop.features.categories;

import com.setec.appcoffeeshop.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static tools.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        var categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        Map<String, Object> respone = new HashMap<>();

        this.categoryService.create(category);
        respone.put("message", "category created");

        return ResponseEntity.ok(respone);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> editById(@PathVariable int id) {
        Map<String, Object> respone = new HashMap<>();

        var category = this.categoryService.findById(id);
        respone.put("category", category);
        return ResponseEntity.ok(respone);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable int id, @RequestBody Category category){
        var categoryExists = this.categoryService.updateById(id, category);

        ApiResponse<Category> respone = new ApiResponse<>(
                true,
                201,
                "Category updated successfully",
                categoryExists,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(respone);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategory(@PathVariable int id) {
       var deleteIten = this.categoryService.deleteById(id);
        ApiResponse<Category> respone = new ApiResponse<>(
                true,
                200,
                "Category deleted successfully",
                deleteIten,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(respone);
    }

    @GetMapping("/paginated")
    public ResponseEntity<?> findPaginated(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) String keyword) {

        var categories = this.categoryService.paginated(pageNo, pageSize, keyword);
        return ResponseEntity.ok(categories);
    }
}

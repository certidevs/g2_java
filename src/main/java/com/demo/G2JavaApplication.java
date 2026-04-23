package com.demo;

import com.demo.model.Category;
import com.demo.model.CategoryType;
import com.demo.model.Review;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ReviewRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class G2JavaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G2JavaApplication.class, args);

        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

        Review re = new Review();
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re);

        Review re2 = new Review();
        re2.setRating(2);
        re2.setComment("Es mejorable");
        re2.setUserVerified(false);
        re2.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re2);

        Review re3 = new Review();
        re3.setRating(4);
        re3.setComment("Buen producto");
        re3.setUserVerified(true);
        re3.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re3);

        Category objects = new Category();
        objects.setName("Vanilla");
        objects.setDescription("Proteins made of vanilla");
        categoryRepository.save(objects);


    }


}

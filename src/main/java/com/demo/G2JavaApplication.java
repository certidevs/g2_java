package com.demo;

import com.demo.model.Review;
import com.demo.repository.ReviewRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class G2JavaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G2JavaApplication.class, args);

        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);

        Review re = new Review();
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re);
//
    }


}

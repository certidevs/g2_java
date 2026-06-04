package com.demo.service;

import com.demo.model.Like;
import com.demo.model.Product;
import com.demo.repository.LikeRepository;
import com.demo.repository.ProductRepository;
import com.demo.model.User;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ProductRepository productRepository;
    public List<Like> findFavoriteProduct(Long userId){
        return likeRepository.findByUser_IdAndProductIsNotNull(userId);
    }
    public boolean toggleProduct(User user, Long productId){
        Optional<Like> existing = likeRepository.findByUser_IdAndProduct_Id(user.getId(), productId);
        if(existing.isPresent()){
            likeRepository.delete(existing.get());
            return false;
        }
        Product product = productRepository.findById(productId).orElseThrow();
        likeRepository.save(Like.builder()
                .user(user)
                .product(product)
                .build());
        return true;
    }

    public Set<Long>findProductsIdsByUserid(Long id) {
        return likeRepository.findProductsIdsByUserid(id);
    }
}

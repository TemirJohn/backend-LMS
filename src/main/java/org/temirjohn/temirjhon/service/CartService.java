package org.temirjohn.temirjhon.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.request.CartRequest;
import org.temirjohn.temirjhon.entity.Cart;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.User;
import org.temirjohn.temirjhon.repository.CartRepository;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.UserRepository;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Cart addToCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId()).orElse(null);
        Course course = courseRepository.findById(cartRequest.getCourseId()).orElse(null);

        if (user != null && course != null) {
            Cart cartItem = new Cart();
            cartItem.setUser(user);
            cartItem.setCourse(course);
            return cartRepository.save(cartItem);
        }
        return null;
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}


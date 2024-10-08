package com.yunussemree.buyer.order;

import com.yunussemree.buyer.cart.Cart;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.orderitem.OrderItem;
import com.yunussemree.buyer.product.Product;
import com.yunussemree.buyer.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService implements IOrderService{

    private final ProductRepository productRepository;
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    public Order createOrder(Cart cart){
        Order order = new Order();
        //order.setUserId(userId); //TODO user will be coded
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }


    public Order createOrderItems(Order order, Cart cart ) {
        cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice()
            );
        }).toList();
    }

    @Override
    public Order saveOrder(Order order) {

    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found when get order by id service!"));
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}

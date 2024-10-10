package com.yunussemree.buyer.order;

import com.yunussemree.buyer.cart.Cart;
import com.yunussemree.buyer.cart.CartService;
import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import com.yunussemree.buyer.orderitem.OrderItem;
import com.yunussemree.buyer.product.Product;
import com.yunussemree.buyer.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final ProductRepository productRepository;
    private final CartService cartService;
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CartService cartService){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
    }


    public Order createOrder(Cart cart){
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }


    public List<OrderItem> createOrderItems(Order order, Cart cart ) {
        return cart.getItems().stream().map(cartItem -> {
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

    @Transactional
    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));

        order.setTotalAmount(cart.getTotalAmount());
        order.setTotalPrice(cart.getTotalPrice());
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId);
        return savedOrder;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found when get order by id service!"));
    } //Todo will be continue

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found when cancel order service!"));
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId){
        return orderRepository.findByUserId(userId);
    }
}

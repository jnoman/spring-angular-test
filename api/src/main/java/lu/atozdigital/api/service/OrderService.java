package lu.atozdigital.api.service;

import java.util.List;

import lu.atozdigital.api.model.Order;

public interface OrderService {
	public Order saveOrder(Order order);
	public Order findOrderById(Long id);
	public boolean existsById(Long id);
	public List<Order> getAllOrders();
}

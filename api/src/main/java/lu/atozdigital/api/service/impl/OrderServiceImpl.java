package lu.atozdigital.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lu.atozdigital.api.model.Order;
import lu.atozdigital.api.repository.OrderRepository;
import lu.atozdigital.api.service.OrderService;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
    private OrderRepository orderRepository;

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order findOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public boolean existsById(Long id) {
		return orderRepository.existsById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

}

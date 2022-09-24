package lu.atozdigital.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lu.atozdigital.api.model.Order;
import lu.atozdigital.api.service.OrderService;

@SpringBootTest
public class OrderTests {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	void saveOrderTest() {
		Order order = new Order();
		order.setReference(UUID.randomUUID().toString());
		order.setCreatedDate(Instant.now());
		orderService.saveOrder(order);
		assertThat(orderService.existsById((long) 1)).isEqualTo(true);
	}
	
	@Test
	void getAllOrdersTest() throws InterruptedException {
		assertThat(orderService.getAllOrders()).isNotEmpty();
	}
	
	@Test
	void findOrderById() throws InterruptedException {
		assertThat(orderService.findOrderById((long) 1)).isNotNull();
	}

}

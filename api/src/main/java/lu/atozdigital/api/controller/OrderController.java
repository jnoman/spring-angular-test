package lu.atozdigital.api.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lu.atozdigital.api.model.Order;
import lu.atozdigital.api.repository.OrderRepository;

@RepositoryRestController
public class OrderController {
	
	@Autowired
    private OrderRepository orderRepository;
	
	@ResponseBody
	@RequestMapping(path = "/orders", method = RequestMethod.POST)
	public ResponseEntity<Object> saveArticle(@RequestBody Order order) throws IOException {
		try {
			order.setReference(UUID.randomUUID().toString());
			orderRepository.save(order);
	        return new ResponseEntity<Object>("ajouter terminer avec succes", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
        
    }

}

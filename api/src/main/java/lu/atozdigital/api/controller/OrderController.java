package lu.atozdigital.api.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lu.atozdigital.api.model.Article;
import lu.atozdigital.api.model.Order;
import lu.atozdigital.api.service.ArticleService;
import lu.atozdigital.api.service.OrderService;
import net.minidev.json.JSONObject;

@RepositoryRestController
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private ArticleService articleService;
	
	@ResponseBody
	@RequestMapping(path = "/orders", method = RequestMethod.POST)
	public ResponseEntity<Object> saveArticle(@RequestBody Order order) throws IOException {
		try {
			order.setReference(UUID.randomUUID().toString());
			orderService.saveOrder(order);
	        return new ResponseEntity<Object>("ajouter terminer avec succes", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
        
    }
	
	@GetMapping("/orders")
    public ResponseEntity<Object> getAllOrders() {
	        return new ResponseEntity<Object>(orderService.getAllOrders(), HttpStatus.OK);
    }
	
	@PutMapping("/orders/{id}")
    public ResponseEntity<Object> updateOrderById(@PathVariable Long id, @RequestBody JSONObject jsonRequest) {
		try {
			if (orderService.existsById(id)) {
				Order order = orderService.findOrderById(id);
				Long articleID = ((Number) jsonRequest.get("articleID")).longValue();
				if (articleService.existsById(id)) {
					Article article = articleService.findArticleById(articleID);
					if(((String) jsonRequest.get("Statu")).equals("add")) {
						if(order.getArticles().contains(article)) {
							return new ResponseEntity<Object>("Order contient déjà cet article", HttpStatus.OK);
						} else {
							order.getArticles().add(article);
							orderService.saveOrder(order);
					        return new ResponseEntity<Object>("l'ajouter d'article sur order terminer avec succes ", HttpStatus.OK);
						}
					}
					else {
						if(!order.getArticles().contains(article)) {
							return new ResponseEntity<Object>("Order ne contient pas cet article", HttpStatus.OK);
						} else {
							order.getArticles().remove(article);
							orderService.saveOrder(order);
					        return new ResponseEntity<Object>("la suppresion d'article sur order terminer avec succes ", HttpStatus.OK);
						}
					}
				} else {
					return new ResponseEntity<Object>("Article is not found", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<Object>("Object is not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

}

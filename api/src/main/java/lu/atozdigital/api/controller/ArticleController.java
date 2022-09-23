package lu.atozdigital.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lu.atozdigital.api.model.Article;
import lu.atozdigital.api.repository.ArticleRepository;

@RepositoryRestController
public class ArticleController {
	
	@Autowired
    private ArticleRepository articleRepository;
     
	@ResponseBody
	@RequestMapping(path = "/articles", method = RequestMethod.POST)
	public ResponseEntity<Object> saveArticle(@RequestPart("article") Article article, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
		try {
	        article.setPicture(multipartFile.getBytes());
	         
	        articleRepository.save(article);
	 
	         
	        return new ResponseEntity<Object>("ajouter terminer avec succes", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
        
    }
	
	@GetMapping("/articles/{id}")
    public ResponseEntity<Object> getArticleById(@PathVariable Long id) {
		try {
			Article article = articleRepository.findById(id).get();
	        return new ResponseEntity<Object>(article, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Article is not found", HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/articles")
    public ResponseEntity<Object> getAllArticles() {
	        return new ResponseEntity<Object>(articleRepository.findAll(), HttpStatus.OK);
    }
}

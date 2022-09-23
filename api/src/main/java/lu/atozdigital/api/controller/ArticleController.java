package lu.atozdigital.api.controller;

import java.io.Console;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lu.atozdigital.api.methode.FileUploadUtil;
import lu.atozdigital.api.modules.Article;
import lu.atozdigital.api.repository.ArticleRepository;

@RepositoryRestController
public class ArticleController {
	
	@Autowired
    private ArticleRepository articleRepository;
     
	@ResponseBody
	@RequestMapping(path = "/articles", method = RequestMethod.POST)
	public ResponseEntity<Object> saveArticle(@RequestPart("article") Article article, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        article.setPicture(fileName);
	         
	        Article savedArticle = articleRepository.save(article);
	 
	        FileUploadUtil.saveFile(savedArticle.getId()+"-"+fileName, multipartFile);
	         
	        return new ResponseEntity<Object>("ajouter terminer avec succes", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
        
    }
	

}

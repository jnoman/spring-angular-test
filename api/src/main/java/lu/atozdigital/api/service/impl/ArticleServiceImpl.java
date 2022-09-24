package lu.atozdigital.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lu.atozdigital.api.model.Article;
import lu.atozdigital.api.repository.ArticleRepository;
import lu.atozdigital.api.service.ArticleService;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Article findArticleById(Long id) {
		return articleRepository.findById(id).get();
	}

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return articleRepository.existsById(id);
	}

}

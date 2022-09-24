package lu.atozdigital.api.service;

import java.util.List;

import lu.atozdigital.api.model.Article;

public interface ArticleService {
	public Article saveArticle(Article article);
	public Article findArticleById(Long id);
	public List<Article> getAllArticles();
	public boolean existsById(Long id);
}

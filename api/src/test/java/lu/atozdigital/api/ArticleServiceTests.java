package lu.atozdigital.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lu.atozdigital.api.model.Article;
import lu.atozdigital.api.model.Order;
import lu.atozdigital.api.service.ArticleService;

@SpringBootTest
public class ArticleServiceTests {

	@Autowired
	private ArticleService articleService;
	
	@Test
	void saveArticleTest() throws URISyntaxException, IOException {
		URL imageURL = new URL("https://miro.medium.com/max/1400/1*b34foqHO-rNFqDH17hRZ4Q.jpeg");
		BufferedImage originalImage=ImageIO.read(imageURL);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		byte[] imageInByte=baos.toByteArray();
		articleService.saveArticle(new Article(null, "article1", 10, imageInByte, new ArrayList<Order>()));
		assertThat(articleService.existsById((long) 1)).isEqualTo(true);
	}
	
	@Test
	void getAllArticlesTest() {
		assertThat(articleService.getAllArticles()).isNotEmpty();
	}
	
	@Test
	void findArticleByIdTest() {
		assertThat(articleService.findArticleById((long) 1)).isNotNull();
	}
	
}

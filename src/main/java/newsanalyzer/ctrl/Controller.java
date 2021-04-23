package newsanalyzer.ctrl;

import newsanalyzer.ui.UserInterface;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.NewsApiException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.util.List;

public class Controller {

	public static final String APIKEY = "76c866eebc25452d9f2ec2bac97a08e5";

	public void process(NewsApi getSearchedNews) {
		System.out.println("Start process");
		List<Article> articles = null;

		//TODO implement Error handling

		//TODO load the news based on the parameters
		try {
			NewsReponse newsResponse = getSearchedNews.getNews();
			articles = newsResponse.getArticles();
			articles.forEach(article -> System.out.println(article.getTitle()));
		} catch (NewsApiException e) {
			System.out.println(e.getMessage());
		}

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}

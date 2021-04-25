package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "76c866eebc25452d9f2ec2bac97a08e5";

	public long getAmountOfArticles(List<Article> data){
		return data.stream().count();
	}

	public String getProviderWithMostArticles(List<Article> data){
		Map<String, Long> frequencyMap = data.stream().collect(Collectors.groupingBy(provider -> provider.getSource().getName(),
				Collectors.counting()));
		String data_temp = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey().toString();

		return data_temp;
	}

	public String getAuthorWithShortestName(List<Article> data){
		return (data.stream().filter(a -> a.getAuthor() != null)
				.sorted(Comparator.comparingInt(a -> a.getAuthor().length())).findFirst().orElse(new Article())).getAuthor();
	}

	public List<Article> getArticlesWithLongestTitle(List<Article> data){
		return data.stream().sorted(Comparator.comparing(Article::getTitle)).collect(Collectors.toList());
	}

	public List<Article> getArticlesSorted(List<Article> data){
		List data_temp = data.stream().sorted(Comparator.comparingInt(sortarticle -> sortarticle.getTitle().
				length())).collect(Collectors.toList());
		Collections.reverse(data_temp);
		return data_temp;
	}

	public void process(NewsApi getSearchedNews) {
		System.out.println("Start process");
		List<Article> articles = null;

		//TODO implement Error handling

		//TODO load the news based on the parameters
		try {
			NewsReponse newsResponse = getSearchedNews.getNews();
			articles = newsResponse.getArticles();
			articles.forEach(article -> System.out.println(article.getTitle()));
			System.out.println();
			System.out.println("Amount of Articles: "+getAmountOfArticles(articles));
			System.out.println("Provider with most Articles: "+getProviderWithMostArticles(articles));
			System.out.println("Author with shortest Name: "+getAuthorWithShortestName(articles));
			System.out.println("Article with longest Title: "+ getArticlesWithLongestTitle(articles));
			System.out.println("Article sorted Alphabetically: "+getArticlesSorted(articles));
			System.out.println();
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

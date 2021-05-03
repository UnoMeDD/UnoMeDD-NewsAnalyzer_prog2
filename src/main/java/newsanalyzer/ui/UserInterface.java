package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.*;
import newsreader.Downloader;
import newsreader.ParallelDownloader;
import newsreader.SequentialDownloader;

import static newsanalyzer.ctrl.Controller.APIKEY;
import static newsanalyzer.ctrl.Controller.urlList;

public class UserInterface 
{
	private Controller ctrl = new Controller();
	private SequentialDownloader seqDown = new SequentialDownloader();
	private ParallelDownloader paraDown = new ParallelDownloader();

	public void getDataFromCtrl1(){
		System.out.println("Choice latest news NFL\"");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("nfl")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(null)
				.setLanguage(Language.en)
				.setSourceCategory(null)
				.setPageSize("100")
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataFromCtrl2(){
		System.out.println("Choice latest news NBA");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("nba")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(null)
				.setLanguage(Language.en)
				.setSourceCategory(null)
				.setPageSize("100")
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDataFromCtrl3(){
		System.out.println("Choice latest news usa");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("usa")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(null)
				.setLanguage(Language.en)
				.setSourceCategory(null)
				.setPageSize("100")
				.createNewsApi();


		ctrl.process(newsApi);

	}
	
	public void getDataForCustomInput() {
		System.out.println("Choice User Input");

		String keyword;
		String endpoint_temp;
		String endpoint;
		String language_temp;
		String language;

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Please set a keyword: ");
		keyword = scanner.nextLine();
		/*System.out.println();
		System.out.println("Please set a Type: a) TOP_HEADLINES b) EVERYTHING");
		endpoint_temp = scanner.nextLine();
		if(endpoint_temp.equals("a")){
			endpoint = "TOP_HEADLINES";
		} else {
			endpoint = "EVERYTHING";
		}
		System.out.println("Please set a Language: a) german b) english");
		language_temp = scanner.nextLine();
		if(language_temp.equals("a")){
			language = "de";
		}else{
			language = "en";
		}
		*/
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(keyword)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(null)
				.setLanguage(Language.en)
				.setSourceCategory(null)
				.setPageSize("100")
				.createNewsApi();

		ctrl.process(newsApi);
	}

	public void getDownloadLastSearchSeq(){
		System.out.println("Download last search Sequential");

		long startTime = System.currentTimeMillis();
		seqDown.process(urlList);
		long endTime = System.currentTimeMillis();
		long timeElapsed = (endTime - startTime)/1000;
		System.out.println("Time taken: "+timeElapsed+"s");
	}

	public void getDownloadLastSearchPara(){
		System.out.println("Download last search Sequential");

		long startTime = System.currentTimeMillis();
		paraDown.process(urlList);
		long endTime = System.currentTimeMillis();
		long timeElapsed = (endTime - startTime);
		System.out.println("Time taken: "+timeElapsed+"ms");
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice latest NFL news", this::getDataFromCtrl1);
		menu.insert("b", "Choice latest NBA news", this::getDataFromCtrl2);
		menu.insert("c", "Choice latest news usa", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("e", "Download last search Sequential", this::getDownloadLastSearchSeq);
		menu.insert("f", "Download last search Parallel", this::getDownloadLastSearchPara);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}

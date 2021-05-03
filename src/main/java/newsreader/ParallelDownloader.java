package newsreader;

import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    ExecutorService exeServ = Executors.newFixedThreadPool(10);

    @Override
    public int process(List<String> urls) {

        int count = 0;
        for (String url : urls) {
            try {
                Future<?> taskStatus = exeServ.submit(() -> {
                    System.out.println(String.format("Starting Thread %s", Thread.currentThread().getName()));
                    saveUrl2File(url);
                });
                count++;
            } catch (Exception e) {
                System.out.println("Article not Found");
            }
        }
        exeServ.shutdown();
        return count;
    }
}

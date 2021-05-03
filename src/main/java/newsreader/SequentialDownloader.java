package newsreader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        int count = 0;
        for (String url : urls) {
            try {
            String fileName = saveUrl2File(url);
                if (fileName != null)
                    count++;
            }catch (Exception e){
                System.out.println("Article not found");
            }
        }
        return count;
    }
}

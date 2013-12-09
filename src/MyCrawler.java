import java.util.Set;


public class MyCrawler {
	/*
	 * 使用种子初始化URL队列
	 * 
	 * @return
	 * 
	 * @Param seeds种子URL
	 */
	private void initCrawerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	/*
	 * 抓取过程
	 * 
	 * @return
	 * 
	 * @param seeds
	 */
	public void crawing(String[] seeds) {
		// 定义过滤器，提取以http://www.lietu.com开头的链接
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith("http://www.lietu.com"))
					return true;
				else
					return false;
			}
		};

		// 初始化URL队列
		initCrawerWithSeeds(seeds);

		while (!LinkQueue.unVisitedUrlisEmpty()
				&& LinkQueue.getVisitedUrlNum() < 1000) {
			// 对头URL出队列
			String visitUrl = (String) LinkQueue.unVisitedUrlDequeue();
			if(visitUrl==null)
				continue;
			System.out.println(visitUrl);
			DownLoadFile downLoader=new DownLoadFile();
			downLoader.downloadFile(visitUrl);
			//
			LinkQueue.addVisitedUrl(visitUrl);
			//
			Set<String> links=HtmlParserTool.extractLinks(visitUrl, filter);
			//
			for(String link:links){
				LinkQueue.addUnvisitedUrl(link);
			}
		}
	}
	
	public static void main(String args[]){
		MyCrawler crawler=new MyCrawler();
		crawler.crawing(new String[]{"http://www.lietu.com","http://www.baidu.com","http://www.lietu.com"});
		
	}

}

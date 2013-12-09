import java.util.Set;


public class MyCrawler {
	/*
	 * ʹ�����ӳ�ʼ��URL����
	 * 
	 * @return
	 * 
	 * @Param seeds����URL
	 */
	private void initCrawerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	/*
	 * ץȡ����
	 * 
	 * @return
	 * 
	 * @param seeds
	 */
	public void crawing(String[] seeds) {
		// �������������ȡ��http://www.lietu.com��ͷ������
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith("http://www.lietu.com"))
					return true;
				else
					return false;
			}
		};

		// ��ʼ��URL����
		initCrawerWithSeeds(seeds);

		while (!LinkQueue.unVisitedUrlisEmpty()
				&& LinkQueue.getVisitedUrlNum() < 1000) {
			// ��ͷURL������
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

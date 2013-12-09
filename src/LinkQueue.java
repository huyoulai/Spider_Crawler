import java.util.HashSet;
import java.util.Set;

public class LinkQueue {
	// 已访问过的url集合
	private static Set visitedUrl = new HashSet();
	// 待访问的url集合
	private static Queue unVisitedUrl = new Queue();

	//
	public static Queue getUnVisitedUrl() {
		return unVisitedUrl;
	}

	//
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}

	//
	public static void removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}

	//
	public static Object unVisitedUrlDequeue() {
		return unVisitedUrl.deQueue();
	}

	//保证每个URL只被访问一次
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url)) {
			unVisitedUrl.enQueue(url);
		}
	}
	
	//获取已访问的url数目
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	
	//
	public static boolean unVisitedUrlisEmpty(){
		return unVisitedUrl.empty();
	}

}

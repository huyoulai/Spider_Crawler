import java.util.HashSet;
import java.util.Set;

public class LinkQueue {
	// �ѷ��ʹ���url����
	private static Set visitedUrl = new HashSet();
	// �����ʵ�url����
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

	//��֤ÿ��URLֻ������һ��
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url)) {
			unVisitedUrl.enQueue(url);
		}
	}
	
	//��ȡ�ѷ��ʵ�url��Ŀ
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	
	//
	public static boolean unVisitedUrlisEmpty(){
		return unVisitedUrl.empty();
	}

}

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class test {
	public static void spider_get() {
		// 创建一个客户端，类似于打开一个浏览器
		HttpClient httpclient = new HttpClient();

		// 创建一个 get 方法，类似于在浏览器地址栏中输入一个地址
		GetMethod getMethod = new GetMethod("http://www.sdfasfbaidu.com");

		try {
			// 回车，获得响应状态码
			int statusCode = httpclient.executeMethod(getMethod);
			System.out.println(statusCode);

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 查看命中情况，可以获得的东西还有很多，比如 head、cookies 等
		String contain;
		try {
			contain = getMethod.getResponseBodyAsString();
			System.out.println("response=" + contain);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 释放
		getMethod.releaseConnection();

	}

	public static void spider_post() {
		// 创建一个客户端，类似于打开一个浏览器
		HttpClient httpclient = new HttpClient();
		// 得到post方法
		String path_url = "http://www.saybot.com/postme";
		PostMethod postMethod = new PostMethod(path_url);

		//
		NameValuePair[] postData = new NameValuePair[2];
		postData[0] = new NameValuePair("武器", "枪");
		postData[0] = new NameValuePair("什么枪", "神枪");
		postMethod.addParameters(postData);

		try {
			int statusCode = httpclient.executeMethod(postMethod);
			// 查看命中情况，可以获得的东西还有很多，比如 head、cookies 等
			System.out.println("response="
					+ postMethod.getResponseBodyAsString());
			// 释放
			postMethod.releaseConnection();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setProxy() {

		// 创建 HttpClient 相当于打开一个代理
		HttpClient httpClient = new HttpClient();

		// 设置代理服务器的 IP 地址和端口
		httpClient.getHostConfiguration().setProxy("192.168.0.1", 9527);

		// 告诉 httpClient，使用抢先认证，否则你会收到“你没有资格”的恶果
		httpClient.getParams().setAuthenticationPreemptive(true);

		// MyProxyCredentialsProvder 返回代理的 credential(username/password)
		// httpClient.getParams().setParameter(CredentialsProvider.PROVIDER,
		// new MyProxyCredentialsProvider());

		// 设置代理服务器的用户名和密码
		httpClient.getState().setProxyCredentials(
				new AuthScope("192.168.0.1", AuthScope.ANY_PORT,
						AuthScope.ANY_REALM),
				new UsernamePasswordCredentials("username", "password"));
	}

	public static void main(String args[]) {
		spider_post();

	}

}

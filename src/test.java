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
		// ����һ���ͻ��ˣ������ڴ�һ�������
		HttpClient httpclient = new HttpClient();

		// ����һ�� get ���������������������ַ��������һ����ַ
		GetMethod getMethod = new GetMethod("http://www.sdfasfbaidu.com");

		try {
			// �س��������Ӧ״̬��
			int statusCode = httpclient.executeMethod(getMethod);
			System.out.println(statusCode);

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �鿴������������Ի�õĶ������кܶ࣬���� head��cookies ��
		String contain;
		try {
			contain = getMethod.getResponseBodyAsString();
			System.out.println("response=" + contain);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ͷ�
		getMethod.releaseConnection();

	}

	public static void spider_post() {
		// ����һ���ͻ��ˣ������ڴ�һ�������
		HttpClient httpclient = new HttpClient();
		// �õ�post����
		String path_url = "http://www.saybot.com/postme";
		PostMethod postMethod = new PostMethod(path_url);

		//
		NameValuePair[] postData = new NameValuePair[2];
		postData[0] = new NameValuePair("����", "ǹ");
		postData[0] = new NameValuePair("ʲôǹ", "��ǹ");
		postMethod.addParameters(postData);

		try {
			int statusCode = httpclient.executeMethod(postMethod);
			// �鿴������������Ի�õĶ������кܶ࣬���� head��cookies ��
			System.out.println("response="
					+ postMethod.getResponseBodyAsString());
			// �ͷ�
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

		// ���� HttpClient �൱�ڴ�һ������
		HttpClient httpClient = new HttpClient();

		// ���ô���������� IP ��ַ�Ͷ˿�
		httpClient.getHostConfiguration().setProxy("192.168.0.1", 9527);

		// ���� httpClient��ʹ��������֤����������յ�����û���ʸ񡱵Ķ��
		httpClient.getParams().setAuthenticationPreemptive(true);

		// MyProxyCredentialsProvder ���ش���� credential(username/password)
		// httpClient.getParams().setParameter(CredentialsProvider.PROVIDER,
		// new MyProxyCredentialsProvider());

		// ���ô�����������û���������
		httpClient.getState().setProxyCredentials(
				new AuthScope("192.168.0.1", AuthScope.ANY_PORT,
						AuthScope.ANY_REALM),
				new UsernamePasswordCredentials("username", "password"));
	}

	public static void main(String args[]) {
		spider_post();

	}

}

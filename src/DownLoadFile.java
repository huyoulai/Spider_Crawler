import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class DownLoadFile {
	/*
	 * ����URL����ҳ����������Ҫ�������ҳ�ļ�����ȥ�� URL �еķ��ļ����ַ�
	 */
	public String getFileNameByUrl(String url, String contentType) {
		//�Ƴ�http
		url = url.substring(7);
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} 
		//��application/pdf����
		else {
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "."
					+ contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}
	
	/*
	 * ������ҳ�ֽ����鵽�����ļ���filePathΪҪ������ļ���Ե�ַ
	 * */
	private void saveToLocal(byte[] data,String filePath){
		try{
			DataOutputStream out=new DataOutputStream(new FileOutputStream(new File(filePath)));
			for(int i=0;i<data.length;i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// ����URLָ������ҳ
	public String downloadFile(String url) {
		String filePath = null;
		//1.����HttpClient�������ò���
		HttpClient httpclient = new HttpClient();
		//����HTTP���ӳ�ʱ 5s
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.����GetMethod�������ò���(����PostMethod)
		//GetMethod getMethod = new GetMethod(url);
		PostMethod getMethod = new PostMethod(url);
		//����get����ʱ 5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//�����������Դ���
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		//3.ִ��HTTP GET����
		try {
			int statusCode = httpclient.executeMethod(getMethod);
			//�жϷ��ʵ�״̬��
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
				filePath = null;
			}
			
			//4.����HTTP��Ӧ����
			byte[] responseBody = getMethod.getResponseBody();
			//������ҳURL���ɱ���ʱ���ļ���
			filePath = "temp\\"
					+ getFileNameByUrl(url,
							getMethod.getResponseHeader("Content-Type")
									.getValue());
			saveToLocal(responseBody,filePath);
		} catch (HttpException e) {
			// �����������쳣��������Э�鲻�Ի��߷��ص�����������
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// ���������쳣
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}

		return filePath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

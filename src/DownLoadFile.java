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
	 * 根据URL和网页类型生成需要保存的网页文件名，去除 URL 中的非文件名字符
	 */
	public String getFileNameByUrl(String url, String contentType) {
		//移除http
		url = url.substring(7);
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} 
		//如application/pdf类型
		else {
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "."
					+ contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}
	
	/*
	 * 保存网页字节数组到本地文件，filePath为要保存的文件相对地址
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
	
	// 下载URL指定的网页
	public String downloadFile(String url) {
		String filePath = null;
		//1.生成HttpClient对象并设置参数
		HttpClient httpclient = new HttpClient();
		//设置HTTP链接超时 5s
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.生成GetMethod对象并设置参数(或用PostMethod)
		//GetMethod getMethod = new GetMethod(url);
		PostMethod getMethod = new PostMethod(url);
		//设置get请求超时 5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		//3.执行HTTP GET请求
		try {
			int statusCode = httpclient.executeMethod(getMethod);
			//判断访问的状态码
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
				filePath = null;
			}
			
			//4.处理HTTP响应内容
			byte[] responseBody = getMethod.getResponseBody();
			//根据网页URL生成保存时得文件名
			filePath = "temp\\"
					+ getFileNameByUrl(url,
							getMethod.getResponseHeader("Content-Type")
									.getValue());
			saveToLocal(responseBody,filePath);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}

		return filePath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

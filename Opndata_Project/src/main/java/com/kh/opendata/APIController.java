package com.kh.opendata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	public static final String serviceKey = "vPhAe28NV6on%2BE2iL8fLSro7mD4cngionumJbQaTKMnQATl%2B5GgZDvyQyoMFF%2FfwLxGDlF1ApCCJk5u3nXgnYg%3D%3D";
	
	@RequestMapping(value="air.do",produces = "application/json; charset=UTF-8")
	public String airPollution(String location) throws IOException{
		
		String url="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+serviceKey;
		url += "&returnType=json";
		url += "&numOfRows=50";//반환받는 결과갚 갯수
		url += "&sidoName="+URLEncoder.encode(location,"UTF-8");
		
		URL requestUrl = new URL(url); //1.요청하고자 하는 url 주소를 매개변수로 전달하면서 객체생성
		
		//2. 생성된 url 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
	
		//3. 전송방식 설정
		urlConnection.setRequestMethod("GET");
		
		//4. 요청주소에 적힌 OpenAPI 서버로 요청 보낸 후 "스트림"을 활용하여 응답데이터 읽어들이기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	
		//5. 반복적으로 응답데이터 읽어들이기
		String responseTest = "";
		String line;
		
		while ( (line=br.readLine())!=null) {
			responseTest+=line;
		}
		System.out.println(responseTest);
		
		//다쓴 자원 반납
		br.close();
		urlConnection.disconnect();
		
		//응답데이터를 보내주고자 하면 문자열값 그대로 넘겨주면 알아서 JSON 형태로 응답됨
		return responseTest;
	}
	
	//xml 형식으로 활용
	@RequestMapping(value="air2.do",produces = "text/xml; charset=UTF-8")
	public String airPollution2(String location) throws IOException{
		
		String url="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+serviceKey;
		url += "&returnType=xml";
		url += "&numOfRows=50";//반환받는 결과갚 갯수
		url += "&sidoName="+URLEncoder.encode(location,"UTF-8");
		
		URL requestUrl = new URL(url); //1.요청하고자 하는 url 주소를 매개변수로 전달하면서 객체생성
		
		//2. 생성된 url 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
	
		//3. 전송방식 설정
		urlConnection.setRequestMethod("GET");
		
		//4. 요청주소에 적힌 OpenAPI 서버로 요청 보낸 후 "스트림"을 활용하여 응답데이터 읽어들이기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	
		//5. 반복적으로 응답데이터 읽어들이기
		String responseTest = "";
		String line;
		
		while ( (line=br.readLine())!=null) {
			responseTest+=line;
		}
		System.out.println(responseTest);
		
		//다쓴 자원 반납
		br.close();
		urlConnection.disconnect();
		
		//응답데이터를 보내주고자 하면 문자열값 그대로 넘겨주면 알아서 JSON 형태로 응답됨
		return responseTest;
	}
}

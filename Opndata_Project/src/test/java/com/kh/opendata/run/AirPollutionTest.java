package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVo;

public class AirPollutionTest {
	
	//한국환경공단_에어코리앙_대기오염정보 -> 시도정보
	
	public static final String serviceKey = "vPhAe28NV6on%2BE2iL8fLSro7mD4cngionumJbQaTKMnQATl%2B5GgZDvyQyoMFF%2FfwLxGDlF1ApCCJk5u3nXgnYg%3D%3D";

	public String [] locations = {"전국","서울","부산","대구","인천","광주","대전","울산","경기","강원","충북","충남","전북","전남","경북","경남","제주","세종"};
	
	@Test
	public void locationTestRun() throws IOException{
		for(String location : locations) {
			testRun(location);
		}
		
	}
	//공공데이터 테스트하기 위한 테스트환경 구성
	//간단한 테스트환경은 src/text/java에 구현하면 된다
	
	//JUnit : 자바 프로그래밍 언어용 유닛 테스트 프레임워크
	//@Test : JUnit이라는 도구를 이용해서 테스트가 가능하도록 설정(테스트하기 위해서 굳이 main메소드를 만들 필요가 없다)
	
	//@Test
	public void testRun(String location) throws IOException{
		
		//OpenAPI 서버로 요청하고자 하는 URL 만들었음
		String url="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+serviceKey;
		url += "&returnType=json";
		url += "&numOfRows=50";//반환받는 결과갚 갯수
		url += "&sidoName="+URLEncoder.encode(location,"UTF-8");
		
		System.out.println(url);
		
		URL requestUrl = new URL(url); //1.요청하고자 하는 url 주소를 매개변수로 전달하면서 객체생성
		
		//2. 생성된 url 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
	
		//3. 전송방식 설정
		urlConnection.setRequestMethod("GET");
		
		//4. 요청주소에 적힌 OpenAPI 서버로 요청 보낸 후 "스트림"을 활용하여 응답데이터 읽어들이기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	
		String responseTest = "";
		String line;
		
		while ( (line=br.readLine()) !=null) {
			responseTest += line;
		}	
		
		//SERVICE_KEY_IS_NOT_REGISTERED_ERROR
		//System.out.println(responsTest);
		
		
		//JsonObject, JSONArray, JSONElement를 이용해서 파싱할 수 있음
		//즉 responseText내에서 원하는 데이터만을 추출할 수 있음
		
		//각각의 item 정보를 airVO 객체에 담고 ArrayList에 차곡차곡 쌓기
		//JsonParser : 문자열 형태의 json 객체를 json object로 변환
		JsonObject totalObj = JsonParser.parseString(responseTest).getAsJsonObject();
		    
		//전체 JSON 형식으로부터 response 속성명에 접근
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		
		int totalCount = bodyObj.get("totalCount").getAsInt();
		
		JsonArray itemsArr = bodyObj.getAsJsonArray("items");
		
		ArrayList<AirVo> list = new ArrayList();
		
		for(int i=0; i<itemsArr.size(); i++) {
			JsonObject item = itemsArr.get(i).getAsJsonObject();
			
			AirVo air = new AirVo();
			air.setStationName(item.get("stationName").getAsString());
			
			if(item.get("dataTime")!=null) {
				air.setDataTime(item.get("dataTime").getAsString());
			}
			
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
		}
		
		//list 내부에 있는 vo객체 확인
		for(AirVo a : list) {
			System.out.println(a);
		}
		
		br.close();
		urlConnection.disconnect();
	}
}

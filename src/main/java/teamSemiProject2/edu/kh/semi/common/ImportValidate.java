package teamSemiProject2.edu.kh.semi.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ImportValidate {

	
	HttpURLConnection httpConn = null;
	String access_token=null;
	URL url=null;
	String restAPIKey = "9357807837942432";
	String  restAPISecret = "4af20e4b01c5ab23dad55215659b9ee1a6971b8c69ac2a2cf612a3183c6576eeff1921a2ed8aa164";
	
	String paymentPath = "https://api.iamport.kr/payments/";

	public int getImportAmount() throws Exception{
		
		
		url = new URL("https://api.iamport.kr/users/getToken");
		httpConn = (HttpURLConnection)url.openConnection();
		
		
		//요청방식 : POST
		httpConn.setRequestMethod("POST");
		// 헤더 설정 json 방식으로 요청
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Accept", "application/json");//받을 데이터 또한 json으로 요청함
		
		httpConn.setDoOutput(true);//outputstream으로 받은 데이터를 던진다
		
		JSONObject jObj = new JSONObject();
		jObj.put("imp_key", restAPIKey);
		jObj.put("imp_secret", restAPISecret);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpConn.getOutputStream()));
		bw.write(jObj.toString());
		bw.flush();
		bw.close();		
		
		int responseCode = httpConn.getResponseCode();

		if(responseCode==200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			StringBuilder sb = new  StringBuilder();
			String line = null;;
			while((line=br.readLine())!=null) {
				sb.append(line+"\n");
				
			}
			br.close();
//			System.out.println(sb.toString());
			JSONParser jsonParse = new JSONParser();

			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject response = (JSONObject) jsonObj.get("response");
			String access_token = (String) response.get("access_token");
//			System.out.println(access_token);
			
			
	        
			
		} else {
			System.out.println(httpConn.getResponseMessage());
		}
		
		
		return 0;
	}
}

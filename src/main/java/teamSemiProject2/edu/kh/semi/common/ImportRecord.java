package teamSemiProject2.edu.kh.semi.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ImportRecord {

	HttpURLConnection httpConn = null;
	String access_token=null;
	URL url=null;
	String path = "https://api.iamport.kr/payments/";
	public long getImportAmount(String imp_uid,String accessToken) throws Exception {

		long importAmount=0;

		
		 url = new URL(path + imp_uid);
		
		
		httpConn = (HttpURLConnection)url.openConnection();
		
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Authorization", accessToken);
		
		httpConn.setDoOutput(true);//outputstream으로 받은 데이터를 던진다

		
	
		
		int responseCode = httpConn.getResponseCode();
//		System.out.println("code:: "+responseCode);
		if(responseCode==200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			StringBuilder sb = new  StringBuilder();
			String line = null;;
			while((line=br.readLine())!=null) {
				sb.append(line+"\n");
				
			}
			br.close();
//			System.out.println(sb.toString());//아임포트 결제 정보 들어옴
			JSONParser jsonParse = new JSONParser();

			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject response = (JSONObject) jsonObj.get("response");
			//java.lang.Long cannot be cast to java.lang.String
			importAmount = (Long)response.get("amount");

	        
		}
		
		return importAmount;
	}
	
}

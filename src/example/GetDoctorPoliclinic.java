/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

 import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.util.regex.*;
/**
 *
 * @author admin
 */
public class GetDoctorPoliclinic {

	private final String USER_AGENT = "Mozilla/5.0";
	private String yandextrans = "https://translate.yandex.net/api/v1.5/tr.json/translate";
	private String keyapi = "&key=trnsl.1.1.20180524T084406Z.60da39d30c21da82.c30c56d84d13f875d87fa4292bbe92ef83b31628";
	private String langdetect = "https://translate.yandex.net/api/v1.5/tr.json/detect";
        private String urlPoliclinic = "https://samozapis-spb.ru/kalininskiy-rayon/poliklinika-no86";
    //



	public static void main(String[] args) throws Exception {

		GetDoctorPoliclinic http = new GetDoctorPoliclinic();

		System.out.println("Testing 1 - Send Http GET request");
		String mystringlang = "Переведи меня собака";
		//кодируем в правильный интернет запрос
		String wordstranslate = java.net.URLEncoder.encode(mystringlang, "UTF-8").replace("+", "%20");
		//http.sendGet(wordstranslate);
		
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();

	}

	// HTTP GET request
	protected String sendGet(String getwords) throws Exception {
		String urlapi = null;
		String langstring = whatlang(getwords); //вызовем определение языка
		//getwords = "%22" + getwords + "%22";
		switch (langstring) {
		case "ru": 
			urlapi = "?lang=ru-en";
			break;
		case "en":
			urlapi = "?lang=en-ru";
			break;
		default: urlapi = "?lang=en-ru";
        break;
		}
	
			
		//System.out.println(getwords);
		//String urlapi = "?lang=ru-en";
		getwords = java.net.URLEncoder.encode(getwords, "UTF-8").replace("+", "%20");

		String url = yandextrans + urlapi + keyapi + "&text=" + getwords;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		return response.toString();

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://translate.yandex.net/api/v1.5/tr.json/translate";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "?lang=en-ru&key=trnsl.1.1.20180524T084406Z.60da39d30c21da82.c30c56d84d13f875d87fa4292bbe92ef83b31628&text=my word";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}
	
	
	// определение языка
	private String whatlang(String getwords) throws Exception {
		
		final Pattern pattern = Pattern.compile("^(.*)(200).*\"(.*)\".*$");
		String stest1 = "";
		String urlapi = "?hint=ru,en";
		getwords = java.net.URLEncoder.encode(getwords, "UTF-8").replace("+", "%20");

		String url = langdetect + urlapi + keyapi + "&text=" + getwords;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		
		System.out.println(response);
		//выдергиваем ланг в соответствии с регуляркой выше
		Matcher matcher = pattern.matcher(response.toString());
			if (matcher.matches()) {
				System.out.println(matcher.groupCount());
				if (matcher.group(3) != null){
		stest1 = matcher.group(3);// + matcher.group(2) + matcher.group(3);
				}
		//if (matcher.matches()) {
			System.out.println( stest1);// + matcher.group(2) + matcher.group(3));
		//}
			}
		
		return stest1;

	}

}
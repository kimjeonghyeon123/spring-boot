package hi.hello;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class 시군구 {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://api.vworld.kr/req/data"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=430A9486-5035-3F51-B4BF-93CB0A1F4533"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("domain","UTF-8") + "=" + URLEncoder.encode("http://localhost:8080", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode("data", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode("2.0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("request","UTF-8") + "=" + URLEncoder.encode("getfeature", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("size","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("geometry","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("attribute","UTF-8") + "=" + URLEncoder.encode("true", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("crs","UTF-8") + "=" + URLEncoder.encode("EPSG:3857", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("geomfilter","UTF-8") + "=" + URLEncoder.encode("BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode("LT_C_ADSIDO_INFO", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray features = jsonObject.getJSONObject("response").getJSONObject("result")
                .getJSONObject("featureCollection").getJSONArray("features");

        List<Map<String, String>> resultList = new ArrayList<>();

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            String sido_code = properties.getString("ctprvn_cd");
            String sido_name = properties.getString("ctp_kor_nm");

            System.out.println(sido_code + " : " + sido_name);
        }
    }
}

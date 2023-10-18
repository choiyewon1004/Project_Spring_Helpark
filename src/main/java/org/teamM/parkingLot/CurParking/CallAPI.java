package org.teamM.parkingLot.CurParking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import net.sf.json.*;

public class CallAPI {
    public static String getCurParking(String addr, String name) throws IOException {
        String addrStr[] = addr.split(" ");
        String district = addrStr[0];

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("495a664f59676864393363796b586b","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("GetParkingInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        urlBuilder.append("/" + URLEncoder.encode(district,"UTF-8")); /* 서비스별 추가 요청인자들*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
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
        String str = sb.toString();

        //API에서 호출한 데이터를 String -> JSON으로 변환
        JSONObject jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(str));

        //jsonobject 변수에서 주차장 정보만을 호출
        JSONObject getParkingInfo = (JSONObject) jsonObject.get("GetParkingInfo");
        JSONArray parkingInfo = (JSONArray) getParkingInfo.get("row");
        int jsonLength = parkingInfo.size();
        JSONObject goalParking = null;

        for(int i = 0; i < jsonLength; i++){
            JSONObject parking = (JSONObject) parkingInfo.getJSONObject(i);
            String goalName = parking.getString("PARKING_NAME");
            if(goalName.contains(name)){
                goalParking = parking;
                break;
            }
        }

        String curParking = goalParking.getString("CUR_PARKING");

        return curParking;
    }
}

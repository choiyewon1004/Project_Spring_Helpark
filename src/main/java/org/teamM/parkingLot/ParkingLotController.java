package org.teamM.parkingLot;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.teamM.Watch.Watch;
import org.teamM.parkingLot.CurParking.CallAPI;
import org.teamM.parkingLot.NsParkingLot.NsParkingLot;
import org.teamM.parkingLot.NsParkingLot.NsParkingLotService;
import org.teamM.parkingLot.NwParkingLot.NwParkingLot;
import org.teamM.parkingLot.NwParkingLot.NwParkingLotDto;
import org.teamM.parkingLot.NwParkingLot.NwParkingLotService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class ParkingLotController {
    //머스테치 스타터가 있기때문에 return에 알아서 머스테치 파일 경로가 붙는다.

    /*
    *RequestMapping
    애플리케이션에서 사용할 bean을 담을 Application Context를 생성하고 초기화
    1)value : url값으로 매핑 조건을 부여.
    2)method: HTTP request 메소드 값을 매핑조건으로 부여. GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE 등이 존재.

    *메소드 종류
    @PostMapping: HTTP Post Method에 해당하는 단축 표현으로 서버에 리소스를 등록할때 사용.
    @GetMapping: HTTP Get Method에 해당하는 단축 표현으로 서버의 리소스를 조회할 때 사용.
    @PutMapping: 서버의 리소스를 모두 수정할 때
    @PatchMapping: 서버의 리소스를 일부수종할 때

     */

    private final NwParkingLotService nwParkingLotService;
    private final NsParkingLotService nsParkingLotService;
    private final String doro_uri = "https://dapi.kakao.com/v2/local/search/address.json";
    private final String key_uri = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private String kakaoLocalKey = "34145ac6a9082b07e92b2dcb1e18d54f"; //EST API 키

    public static Watch watch = new Watch();
    public static CallAPI callAPI = new CallAPI();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/map")
    public String map(Model model) {
        int startTime = watch.getTime();
        int endTime = startTime;

        if(startTime < 500){
            endTime = startTime + 2400;
        }

        model.addAttribute("Open_nw", nwParkingLotService.findOpenNwParkingLot(startTime, endTime));
        model.addAttribute("Close_nw", nwParkingLotService.findCloseNwParkingLot(startTime, endTime));
        model.addAttribute("Open_ns", nsParkingLotService.findOpenNsParkingLot(startTime, endTime));
        model.addAttribute("Close_ns", nsParkingLotService.findCloseNsParkingLot(startTime, endTime));

        System.out.println(nsParkingLotService.findOpenNsParkingLot(startTime, endTime));
        return "map";
    }

    @RequestMapping(value = "/available", method = {RequestMethod.POST})
    @ResponseBody
    public String available(@RequestParam("keyName") String keyName, @RequestParam("keyAddr") String keyAddr ) throws IOException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>백 작동");
        String district = keyAddr.split(" ")[0];
        System.out.println(district);
        System.out.println(keyName);

        String curParking = callAPI.getCurParking(keyAddr, keyName);
        //return과 관련된 부분
        System.out.println(curParking);

        return curParking;
    }

    // 검색
    @RequestMapping(value = "/search.action", method = { RequestMethod.POST })
    @ResponseBody
    public HashMap<String , ArrayList<ParkingLot>> test(@RequestParam("search_text") String search_text) {
        HashMap<String ,ArrayList<ParkingLot>> result_data;

        double[] search_loc =getCoordinate(search_text);
        System.out.println(search_text+ "= "+ search_loc[0] +", "+search_loc[1]);

        result_data = search_jpa_total(search_loc[0], search_loc[1]);
        System.out.println(result_data.get("Open"));

        return result_data;
    }

    //현재위치
    @RequestMapping(value = "/current.do",method =  RequestMethod.POST )
    @ResponseBody
    public HashMap<String ,ArrayList<ParkingLot>> post(@RequestParam(value="lat1", required=false) Double here_x,@RequestParam(value="lng1", required=false) Double here_y) throws Exception {
        HashMap<String ,ArrayList<ParkingLot>> result_data;
        System.out.println(here_x);
        System.out.println(here_y);

        result_data = search_jpa_total(here_y, here_x);
        System.out.println(result_data.size());

        System.out.println(result_data);

        return result_data;
    }

    public ParkingLot NwToPark(NwParkingLot NwInfo){
        ParkingLot res = new ParkingLot();

        res.setId(NwInfo.getId());
        res.setAddr(NwInfo.getAddr());
        res.setParking_name(NwInfo.getParking_name());
        res.setLat(NwInfo.getLat());
        res.setLng(NwInfo.getLng());
        res.setWeekdayBeginTime(NwInfo.getWeekdayBeginTime());
        res.setWeekdayEndTime(NwInfo.getWeekdayEndTime());
        res.setWeekendBeginTime(NwInfo.getWeekendBeginTime());
        res.setWeekendEndTime(NwInfo.getWeekendEndTime());
        res.setRates(NwInfo.getRates());
        res.setCapacity(NwInfo.getCapacity());
        return res;
    }

    public ParkingLot NsToPark(NsParkingLot NsInfo){
        ParkingLot res = new ParkingLot();

        res.setId(NsInfo.getId());
        res.setAddr(NsInfo.getAddr());
        res.setParking_name(NsInfo.getParking_name());
        res.setLat(NsInfo.getLat());
        res.setLng(NsInfo.getLng());
        res.setWeekdayBeginTime(NsInfo.getWeekdayBeginTime());
        res.setWeekdayEndTime(NsInfo.getWeekdayEndTime());
        res.setWeekendBeginTime(NsInfo.getWeekendBeginTime());
        res.setWeekendEndTime(NsInfo.getWeekendEndTime());
        res.setRates(NsInfo.getRates());
        res.setCapacity(NsInfo.getCapacity());
        return res;
    }
    // Open-jpa
    public HashMap<String ,ArrayList<ParkingLot>> search_jpa_total(Double here_x, Double here_y){
        HashMap<String ,ArrayList<ParkingLot>> result_data = new HashMap<String, ArrayList<ParkingLot>>();

        List<NwParkingLot> query_data_open_nw =  nwParkingLotService.findRadiusOpenNwParkingLot(BigDecimal.valueOf(here_x), BigDecimal.valueOf(here_y));
        List<NsParkingLot> query_data_open_ns =  nsParkingLotService.findRadiusOpenNsParkingLot(BigDecimal.valueOf(here_x), BigDecimal.valueOf(here_y));

        List<NwParkingLot> query_data_close_nw =  nwParkingLotService.findRadiusCloseNwParkingLot(BigDecimal.valueOf(here_x), BigDecimal.valueOf(here_y));
        List<NsParkingLot> query_data_close_ns =  nsParkingLotService.findRadiusCloseNsParkingLot(BigDecimal.valueOf(here_x), BigDecimal.valueOf(here_y));


        ArrayList<ParkingLot> res_open_data = new ArrayList<ParkingLot>();
        for(int i=0;i<query_data_open_nw.size();i++) {
            res_open_data.add(NwToPark(query_data_open_nw.get(i)));
        }
        for(int i=0;i<query_data_open_ns.size();i++) {
            res_open_data.add(NsToPark(query_data_open_ns.get(i)));
        }
        result_data.put("Open", res_open_data);

        ArrayList<ParkingLot> res_close_data = new ArrayList<ParkingLot>();
        for(int i=0;i<query_data_close_nw.size();i++) {
            res_close_data.add(NwToPark(query_data_close_nw.get(i)));
        }
        for(int i=0;i<query_data_close_ns.size();i++) {
            res_close_data.add(NsToPark(query_data_close_ns.get(i)));
        }
        result_data.put("Close", res_close_data);

        return result_data;
    }

    public double[] getCoordinate(String qAddr){

        double[] result = {0,0};

        try{
            RestTemplate restTemplate = new RestTemplate();

            String apiKey = "KakaoAK " + kakaoLocalKey;
            String address = qAddr;

            // 요청 헤더에 만들기, Authorization 헤더 설정하기
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            UriComponents uriComponents = UriComponentsBuilder
                    .fromHttpUrl(doro_uri)
                    .queryParam("query",address)
                    .build();

            ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

            // API Response로부터 body 뽑아내기
            String body = response.getBody();
            JSONObject json = new JSONObject(body);
            // body에서 좌표 뽑아내기
            JSONArray documents = json.getJSONArray("documents");
            String x = documents.getJSONObject(0).getString("x");
            String y = documents.getJSONObject(0).getString("y");

            result[0] = Double.parseDouble(x);
            result[1] = Double.parseDouble(y);
        }catch(Exception e) {

            // 키워드 검색
            RestTemplate restTemplate = new RestTemplate();

            String apiKey = "KakaoAK " + kakaoLocalKey;
            String address = qAddr;

            // 요청 헤더에 만들기, Authorization 헤더 설정하기
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            UriComponents uriComponents = UriComponentsBuilder
                    .fromHttpUrl(key_uri)
                    .queryParam("query", address)
                    .build();

            ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

            // API Response로부터 body 뽑아내기
            String body = response.getBody();
            JSONObject json = new JSONObject(body);
            // body에서 좌표 뽑아내기
            JSONArray documents = json.getJSONArray("documents");
            String x = documents.getJSONObject(0).getString("x");
            String y = documents.getJSONObject(0).getString("y");

            result[0] = Double.parseDouble(x);
            result[1] = Double.parseDouble(y);
        }

        //System.out.println(result[0]+" : "+result[1]);
        return result;
    }

    // unit : kilometer, meter 작성
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    // for 문으로 검색값 찾기
    public ArrayList<NwParkingLotDto> search_data(Double here_x, Double here_y){
        ArrayList<NwParkingLotDto> result_data = new ArrayList<NwParkingLotDto>() ;
        int startTime = watch.getTime();
        int endTime = startTime;

        if(startTime < 500){
            endTime = startTime + 2400;
        }

        List<NwParkingLot> query_data = nwParkingLotService.findOpenNwParkingLot(startTime, endTime) ;

        int len_data = query_data.size();
        for(int i=0;i<len_data;i++){
            Double new_x = query_data.get(i).getLat();
            Double new_y = query_data.get(i).getLng();
            Double len_dis = distance(here_x,here_y,new_x, new_y,"killometer");

            if(len_dis<2){
                result_data.add(new NwParkingLotDto(query_data.get(i)));
            }
        }
        System.out.println(query_data.size()+">>"+result_data.size());
        return result_data;
    }

}

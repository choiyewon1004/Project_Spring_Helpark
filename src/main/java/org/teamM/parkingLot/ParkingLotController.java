package org.teamM.parkingLot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.teamM.parkingLot.NwParkingLot.NwParkingLotService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    public Integer getTime(){
        //현재시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);
        int timeNow = Integer.parseInt(formatedNow);

        return timeNow;
    }
    @GetMapping("/")
    public String index(Model model) {

        int startTime = getTime();
        int endTime = startTime;

        if(startTime < 500){
            endTime = startTime + 2400;
        }

        model.addAttribute("Open", nwParkingLotService.findOpenNwParkingLot(startTime, endTime));
        model.addAttribute("Close", nwParkingLotService.findCloseNwParkingLot(startTime, endTime));
        return "index";
    }
}

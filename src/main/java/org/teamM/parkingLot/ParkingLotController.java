package org.teamM.parkingLot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ParkingLotController {
    //머스테치 스타터가 있기때문에 return에 알아서 머스테치 파일 경로가 붙는다.
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

package org.teamM.parkingLot.NwParkingLot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NwParkingLotService {
    /*
    스프링에서 Bean 주입받는 방식
    -@Autowired
    -setter
    -생성자

    *생성자 주입 방식
    -가장 권하는 Bean 주입 방식.
    -@RequiredArgsConstrucotr가 생성자 해결
    ->final 선언된 모든 필드를 인자값으로 생성
    -롬복을 쓰면 편하다 -> 의존성 변경해줄 때 수정 필요없음.
     */
//    @Autowired
    private final NwParkingLotRepository NwParkingLotRepository;

    //요일 구하는 함수 월:1 ~ 일:7
    public Integer getDate(){
        // 1. LocalDate 생성
        LocalDate date = LocalDate.now();

        // 2. DayOfWeek 객체 구하기
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 3. 숫자 요일 구하기
        int dayOfWeekNumber = dayOfWeek.getValue();

        return dayOfWeekNumber;
    }




    /*
     - 조회API는 테스트 코드 예제가 없음
     -> 주로
     */

    @Transactional(readOnly = true)
    public List<NwParkingLot> findOpenNwParkingLot(int time1, int time2) {
        int date = getDate();
        if(date == 6 || date == 7) {
            System.out.println("Open 주말 작동");
            return NwParkingLotRepository.findByWeekendBeginTimeLessThanAndWeekendEndTimeGreaterThan(time1, time2);
        }
        List<NwParkingLot> test = NwParkingLotRepository.findByWeekendBeginTimeLessThanAndWeekendEndTimeGreaterThan(time1, time2);
        System.out.println("Open 주중 List 크기" + test.size());
        System.out.println("Open 주중 작동");
        return NwParkingLotRepository.findByWeekdayBeginTimeLessThanAndWeekdayEndTimeGreaterThan(time1, time2);
    }

    @Transactional(readOnly = true)
    public List<NwParkingLot> findCloseNwParkingLot(int time1, int time2){
        int date = getDate();
        if(date == 6 || date == 7){
            System.out.println("Close 주말 작동");
            return NwParkingLotRepository.findByWeekendBeginTimeGreaterThanOrWeekendEndTimeLessThan(time1, time2);
        }
        List<NwParkingLot> test = NwParkingLotRepository.findByWeekdayBeginTimeGreaterThanOrWeekdayEndTimeLessThan(time1, time2);
        System.out.println("Close 주중 List 크기" + test.size());
        System.out.println("Close 주중 작동");
        return NwParkingLotRepository.findByWeekdayBeginTimeGreaterThanOrWeekdayEndTimeLessThan(time1, time2);
    }

}

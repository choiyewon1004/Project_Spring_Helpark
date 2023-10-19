package org.teamM.parkingLot.NsParkingLot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamM.Watch.Watch;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RequiredArgsConstructor
@Service
public class NsParkingLotService {
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
    private final org.teamM.parkingLot.NsParkingLot.NsParkingLotRepository NsParkingLotRepository;

    public static Watch watch = new Watch();

    /*
     - 조회API는 테스트 코드 예제가 없음
     -> 주로
     */

    @Transactional(readOnly = true)
    public List<NsParkingLot> findOpenNsParkingLot(int time1, int time2) {
        int date = watch.getDate();
        if(date == 6 || date == 7) {
            System.out.println("Open 주말 작동");
            return NsParkingLotRepository.findByWeekendBeginTimeLessThanAndWeekendEndTimeGreaterThan(time1, time2);
        }
        List<NsParkingLot> test = NsParkingLotRepository.findByWeekendBeginTimeLessThanAndWeekendEndTimeGreaterThan(time1, time2);
        System.out.println("Open 주중 List 크기" + test.size());
        System.out.println("Open 주중 작동");
        return NsParkingLotRepository.findByWeekdayBeginTimeLessThanAndWeekdayEndTimeGreaterThan(time1, time2);
    }

    @Transactional(readOnly = true)
    public List<NsParkingLot> findCloseNsParkingLot(int time1, int time2){
        int date = watch.getDate();
        if(date == 6 || date == 7){
            System.out.println("Close 주말 작동");
            return NsParkingLotRepository.findByWeekendBeginTimeGreaterThanOrWeekendEndTimeLessThan(time1, time2);
        }
        List<NsParkingLot> test = NsParkingLotRepository.findByWeekdayBeginTimeGreaterThanOrWeekdayEndTimeLessThan(time1, time2);
        System.out.println("Close 주중 List 크기" + test.size());
        System.out.println("Close 주중 작동");
        return NsParkingLotRepository.findByWeekdayBeginTimeGreaterThanOrWeekdayEndTimeLessThan(time1, time2);
    }

    @Transactional(readOnly = true)
    public List<NsParkingLot> findRadiusOpenNsParkingLot(BigDecimal hx, BigDecimal hy) {
        int date = watch.getDate();
        if(date == 6 || date == 7) {
            System.out.println("Open 주말 작동");
            return NsParkingLotRepository.findByRadiusWeekendOpen(hx,hy,3000, watch.getTime());
        }
        List<NsParkingLot> test = NsParkingLotRepository.findByRadiusWeekdayOpen(hx,hy,3000, watch.getTime());
        System.out.println("Open 주중 List 크기" + test.size());
        System.out.println("Open 주중 작동");
        return NsParkingLotRepository.findByRadiusWeekdayOpen(hx,hy,3000, watch.getTime());
    }

    public List<NsParkingLot> findRadiusCloseNsParkingLot(BigDecimal hx, BigDecimal hy) {
        int date = watch.getDate();
        if(date == 6 || date == 7) {
            System.out.println("Close 주말 작동");
            return NsParkingLotRepository.findByRadiusWeekendClose(hx,hy,3000, watch.getTime());
        }
        List<NsParkingLot> test = NsParkingLotRepository.findByRadiusWeekdayClose(hx,hy,3000, watch.getTime());
        System.out.println("Close 주중 List 크기" + test.size());
        System.out.println("Close 주중 작동");
        return NsParkingLotRepository.findByRadiusWeekdayClose(hx,hy,3000, watch.getTime());
    }

}

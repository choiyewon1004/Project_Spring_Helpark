package org.teamM.parkingLot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /*
     - 조회API는 테스트 코드 예제가 없음
     -> 주로
     */
    @Transactional(readOnly = true)
    public List<NwParkingLotDto> findAll(){
        List<NwParkingLotDto> test = NwParkingLotRepository.findAll().stream().map(NwParkingLotDto::new).collect(Collectors.toList());
        System.out.println(test);
        return NwParkingLotRepository.findAll().stream().map(NwParkingLotDto::new).collect(Collectors.toList());
    }

}

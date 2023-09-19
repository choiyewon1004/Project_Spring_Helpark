package org.teamM.parkingLot;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@SpringBootTest , TestRestTemplate
JPA기능까지 한꺼번에 테스트할 때 사용한다.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NwParkingLotRepositoryTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//    @Autowired
//    NwParkingLotRepository NwParkingLotRepository;
//
////    /*
////    @After
////    - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정해준다.
////    - 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해서 사용
////    - 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있다.
////     */
//    @After
//    public void cleanup(){
//        NwParkingLotRepository.deleteAll();
//    }
//
//    @Test
//    public void 주차장_불러오기() {
//        //when
//        List<NwParkingLot> nwParkingLotList = NwParkingLotRepository.findAll();
//    }
}

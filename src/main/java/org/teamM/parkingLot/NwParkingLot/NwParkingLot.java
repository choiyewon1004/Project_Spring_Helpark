package org.teamM.parkingLot.NwParkingLot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
*추가 설명
-(1) 서비스 초기 구축 단계에서는 테이블 설계가 빈번하게 변경되는데, 이 때 롬복의 어노테이션들은 코드 변경량을 최소화 시켜주기때문에 적극 사용.
-(2) Entity 클래스에는 Setter 메소드를 만들지 않는다. 값 변경 필요시 목적과 의도를 나타낼 수 있는 메소드를 추가해준다.
 */
@Getter // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor //Lombok 포함 기능 -> 기본 생성자를 자동으로 생성해준다.
@Table(name = "nw_parking_lot")
@Entity //테이블과 연결될 클래스임을 뜻한다.
public class NwParkingLot {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK 생성 규칙
    private Long id;

    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다. 기본값 외에 추가로 변경할 경우 사용한다.
    @Column(length=50, nullable = false)
    private String addr;

    private String parking_name;

    private Double lat;

    private Double lng;

    private Integer weekdayBeginTime;

    private Integer weekdayEndTime;

    private Integer weekendBeginTime;

    private Integer weekendEndTime;

    @Column(name="rates")
    private Integer rates;

    private Integer time_rate;

    private Integer add_rates;

    private Integer add_time_rate;

    private Integer capacity;
}

package org.teamM.parkingLot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@Setter
@NoArgsConstructor //Lombok 포함 기능 -> 기본 생성자를 자동으로 생성해준다.
@Entity //테이블과 연결될 클래스임을 뜻한다.
public class ParkingLot {

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

    private Integer capacity;
}
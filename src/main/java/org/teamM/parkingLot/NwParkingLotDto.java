package org.teamM.parkingLot;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
*Dto 클래스
-Entity 클래스와 유사해 보일수 잇다.
-하지만 Entity클래스는 데이터베이스와 맞닿은 핵심클래스로 DB 구조 변경하기 때문에
Request/Reponse 반응이 일어나는 클래스로 사용해서는 안된다.
 */

/*
Dto는 Entity 필드 중 일부만 사용하므로 생성자로 Entity를 받아 필드에 넣는다.
 */
@Getter
@NoArgsConstructor
public class NwParkingLotDto {
    //DTO에서 사용할 필드(엔티티에서 불러오는 것)
    private String parking_code;
    private String parking_name;
    private String lat;
    private String lng;

    //엔티티에서 필드값을 불러옴
    public NwParkingLotDto(NwParkingLot entity) {
        this.parking_code = entity.getParking_code();
        this.parking_name = entity.getParking_name();
        this. lat = entity.getLat();
        this.lng = entity.getLng();
    }
}

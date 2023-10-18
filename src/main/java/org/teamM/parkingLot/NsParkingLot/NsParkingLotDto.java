package org.teamM.parkingLot.NsParkingLot;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NsParkingLotDto {
    //DTO에서 사용할 필드(엔티티에서 불러오는 것)
    private Long id;
    private String parking_name;
    private Double lat;
    private Double lng;
    private Integer weekdayBeginTime;
    private Integer weekdayEndTime;

    //엔티티에서 필드값을 불러옴
    public NsParkingLotDto(NsParkingLot entity) {
        this.id = entity.getId();
        this.parking_name = entity.getParking_name();
        this. lat = entity.getLat();
        this.lng = entity.getLng();
        this.weekdayBeginTime = entity.getWeekdayBeginTime();
        this.weekdayEndTime = entity.getWeekdayEndTime();
    }
}
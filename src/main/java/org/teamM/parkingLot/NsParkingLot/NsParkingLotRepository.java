package org.teamM.parkingLot.NsParkingLot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface NsParkingLotRepository extends JpaRepository<NsParkingLot, String> {
    //주중 영업시간인 주차장 불러오기
    List<NsParkingLot> findByWeekdayBeginTimeLessThanAndWeekdayEndTimeGreaterThan (int weekdayBeginTime, int weekdayEndTime);

    //주중 영업시간이 아닌 주차장 불러오기
    List<NsParkingLot> findByWeekdayBeginTimeGreaterThanOrWeekdayEndTimeLessThan (int weekdayBeginTime, int weekdayEndTime);

    //주말 영업시간 중인 주차장 불러오기
    List<NsParkingLot> findByWeekendBeginTimeLessThanAndWeekendEndTimeGreaterThan (int weekendBeginTime, int weekendEndTime);

    //주말 영업시간이 아닌 주차장 불러오기
    List<NsParkingLot> findByWeekendBeginTimeGreaterThanOrWeekendEndTimeLessThan (int weekendBeginTime, int weekendEndTime);


    //반경 주차장 불러오기 - 주중 영업시간
    @Query(value ="SELECT *, ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng,lat))AS distance FROM ns_parking_lot WHERE (ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng, lat)) < :distanceLevel )AND((weekday_begin_time < :now_time)AND(:now_time < weekday_end_time))" ,nativeQuery = true)
    List<NsParkingLot> findByRadiusWeekdayOpen(@Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude, @Param("distanceLevel") int distanceLevel , @Param("now_time") int now_time);

    //반경 주차장 불러오기 - 주중 영업시간 아닌 주차장
    @Query(value ="SELECT *, ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng,lat))AS distance FROM ns_parking_lot WHERE (ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng, lat)) < :distanceLevel)AND((weekday_begin_time > :now_time)OR(:now_time > weekday_end_time))" ,nativeQuery = true)
    List<NsParkingLot> findByRadiusWeekdayClose(@Param("longitude")BigDecimal longitude, @Param("latitude") BigDecimal latitude, @Param("distanceLevel") int distanceLevel , @Param("now_time") int now_time);

    //반경 주차장 불러오기 - 주말 영업시간
    @Query(value ="SELECT *, ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng,lat))AS distance FROM ns_parking_lot WHERE (ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng, lat)) < :distanceLevel)AND((weekend_begin_time < :now_time)AND(:now_time < weekend_end_time))" ,nativeQuery = true)
    List<NsParkingLot> findByRadiusWeekendOpen(@Param("longitude")BigDecimal longitude, @Param("latitude") BigDecimal latitude, @Param("distanceLevel") int distanceLevel , @Param("now_time") int now_time);

    //반경 주차장 불러오기 - 주말 영업시간 아닌 주차장
    @Query(value ="SELECT *, ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(lng,lat))AS distance FROM ns_parking_lot WHERE (ST_Distance_Sphere(:longitude, :latitude), POINT(lng, lat)) < :distanceLevel)AND((weekend_begin_time > :now_time)OR(:now_time > weekend_end_time))" ,nativeQuery = true)
    List<NsParkingLot> findByRadiusWeekendClose(@Param("longitude")BigDecimal longitude, @Param("latitude") BigDecimal latitude, @Param("distanceLevel") int distanceLevel , @Param("now_time") int now_time);

}
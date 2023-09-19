package org.teamM.parkingLot;


import org.springframework.data.jpa.repository.JpaRepository;

/*
*Repository 클래스란?
-ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer가 있다
-JPA서는 이를 Repository라고 부르며 '인터페이스'로 생성한다.
-이후 JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 자동으로 생성된다.

*주의사항
-Entity 클래스와 기본 Repository는 함께 위치해야 한다.
-> Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없다.
 */
public interface NwParkingLotRepository extends JpaRepository<NwParkingLot, String> {
}

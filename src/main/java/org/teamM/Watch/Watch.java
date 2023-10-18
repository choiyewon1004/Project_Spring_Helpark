package org.teamM.Watch;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Watch {
    public Integer getTime(){
        //현재시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        // 포맷 적용하기
        String formatedNow = now.format(formatter);
        int timeNow = Integer.parseInt(formatedNow);

        return timeNow;
    }

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
}

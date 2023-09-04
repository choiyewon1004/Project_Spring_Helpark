package org.teamM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* Application 클래스
- 앞으로 만들 프로젝트의 메인 클래스

* @SpringBootApplication
-스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정.
    ->프로젝트 최상단에 위치해야만 한다.
*/
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        /*
         * 내장WAS 서버 실행 -> Jar파일 실행하면 된다.

         * 내장WAS 사용 이유 -> 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있다.
         */
        SpringApplication.run(Application.class, args);
    }
}

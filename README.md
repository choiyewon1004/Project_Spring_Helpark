## Helpark : 서울 열린데이터 광장의 데이터를 활용해 만든 주차장 찾기 웹 어플리케이션 서비스(개발 진행중)

<br/><br/><br/>

## 1. 소개

### (참고 : 최종 정리는 프로젝트 MVP 완성후 작성할 예정입니다.)

### 1) 배포중인 화면


<div align="center">
<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/0d0b3704-89d3-4fa7-80b7-51b38a84f5e7" width="220" height="400" />

<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/dfe258ec-a8fa-4609-b22e-0efb315349d1" width="220" height="400" />

<br/>


<이미지 - (좌) 메인화면 (우) 지도화면>
</div>


### 2) 배포 URL
* [Helpark 링크](http://3.34.114.142:8080/)

<br/>

### 3) 프로젝트 블로그
* [블로그 주소](https://www.notion.so/spring-project-8fe4731d22f244f4a01dc02729e33338)
* [개발 일지](https://www.notion.so/d19aa2c0c05443c499a1e7a5372648b0?v=01075cb1917546e5bc90d51e4cf76307)
  (주 : 석의 - 본인 | 예원, 태성 - 팀원)
  
<br/><br/><br/>



## 2. 프로젝트 설명
### 1) 개요

* 진행 기간: 2023.8.24 ~ (진행중)
* 개발 인원 : 3명
* 개발 환경
   
  (1) 언어
  - Java
  - HTML/CSS/Javascript
    
  (2) 프레임워크
  - 스프링부트
  - 도커
  
  (3) IDE
  - IntelliJ
  - Android Studio<br>

  (4) 활용데이터
  - 서울시 시영주차장 실시간 주차대수 정보

  (5) 제작 배경
  - **Spring Boot , GitHub Actions, AWS EC2 | RDS, Docker, Flutter 를 활용한 풀 사이클 개발을 위해**
  - **Android Studio를 통해 웹을 웹뷰로 감싼 후 출시하여 실제 사용을 통해 트래픽 관리를 위해**
  - **추후 사용 결과를 통해 추가적인 기능을 만들며 지속적인 애플리케이션 관리를 위해**
<br/>

### 2) 제작 과정
#### (1) 서비스 기획
&ensp; HelPark는 운전 중 사용이 간편한 공영 주차장 검색 웹, 모바일 애플리케이션을 목표로 기획했습니다.
운전 중 근처의 주차장을 찾아야 할 때가 있습니다. 기존 지도 어플리케이션과 , 주차장 어플리케이션은 부가적인 기능이 많아 필요한 주차장 정보를 찾느라 오랜 시간 핸드폰을 사용하는 경우가 많습니다.

&ensp; 운전 중 핸드폰을 사용하는 것은 매우 위험한 행위입니다. 실제로 운전자들은 운전 중 휴대전화를 사용하는 것을 매우 위험한 행위로 인식하고 있습니다. 또한 운전 중 핸드폰 사용으로 인한 사고율은 
21년 기준 3~40%로 보고됐습니다. 따라서 주차장을 찾기 위해 핸드폰을 오래 사용하는 것을 막아 사고율을 낮추기 위한 애플리케이션을 목표합니다.

<br/>

#### (2) 요구사항 정리
<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/6ee863b9-5324-4c58-b648-e2e0715d7986" />

<br/>

#### (3) IA 설계
<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/e194a191-7d52-4ac7-9400-a3f75e767ae8" />

<br/>

#### (4) ERD 설계
<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/47b2cdae-a6ea-4210-93ab-9bba85b42807" />

<br/>

#### (5) 배포 구조
<img src="https://github.com/MeanOfRedStone/portfolio/assets/128448869/49874df1-2e44-4b51-9432-19ee12f5e434" />




# 📁 the-more-the-better
## 다다익선
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'
<br><br>
배달료를 공동으로 부담하기 위해 음료를 함께 구매할 수 있도록 일정 인원을 모집해주는 서비스
<br>

## 👩‍💻 팀원 소개
#### • 🧑🏻‍🦰 유재혁(팀장) : QA, 발표 및 자료구성, 깃 프로젝트 관리 
#### • 👩🏻 박주현(Backend) : 회원관리 기능 구현, 주문처리 기능 구현, DB 모델링
#### • 👦🏻 박준수(Frontend) : API를 통해 주문 화면 구현, 비동기 처리, 주문처리 기능 구현
#### • 👩🏻‍🦰 박채연(Frontend) : Front 디자인, 가게 정보, 비동기 처리, 페이지 구성 관리 -> 가게 정보가 뭘 뜻하는 건지 잘 모르겠음.. 채연이가 수정해줘 ~
<br>

## 📚 프로젝트 설명
### <strong> 1. Technology of Untact<br> </strong>
코로나 바이러스로 인한 사회적 거리 두기가 지속적으로 실시되고 있는 가운데 비 대면 서비스의 중요도가 높아지고 있습니다.  사람이 여럿 모이는 카페를 직접 방문하지 않고 배달주문을 통해 매장을 이용할 수 있는 방법을 찾고자 하였습니다. 

### <strong> 2. Cost-effectiveness<br>  </strong>
음료를 일정 인원을 모아 다른 구매자들과 동시간에 함께 구매하여 배달 료를 공동 부담하는 서비스를 제시합니다.
### <strong> 3. Accessibility<br>  </strong>
공동구매자를 실시간 모집할 수 있고 모집이 완료된 후 문자서비스를 통해 함께 상품을 주문할 수 있도록 유도합니다.
<br><br>

## 📝 사용언어, 기술스택
![Generic badge](https://img.shields.io/badge/platform-Web-brightgreen.svg) ![Generic badge](https://img.shields.io/badge/library-vue-blue.svg) ![Generic badge](https://img.shields.io/badge/framework-spring-green.svg)
![Generic badge](https://img.shields.io/badge/database-ORACLE-yellow.svg) ![Generic badge](https://img.shields.io/badge/api-kakaomap-red,.svg) ![Generic badge](https://img.shields.io/badge/language-Java,JavaScript-important.svg)
<br>

### 세부 기술
| 기술 | 설명 |
| -------- | ---- |
| SpringBoot | 스프링 기반 애플리케이션 |
| JPA | 관계형 데이터베이스 사용방식 정의 인터페이스 |
| Log4j | 프로그램 작성시 로그를 남기기 위해 사용되는 자바 기반 로깅 유틸리티 |
| REST API | REST 아키텍처의 제약 조건을 준수하는 애플리케이션 프로그래밍 인터페이스 |
| Oracle RDBMS | Oracle의 관계형 데이터베이스 관리 시스템 |
| Builder Pattern | 복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴 |
| MVC Pattern | 복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴 |


| Swagger | ![Generic badge](https://img.shields.io/badge/release-2.8.0-blue.svg)| 팀용 API 문서 및 디자인 도구 |
| JUnit4 | ![Generic badge](https://img.shields.io/badge/release-4.7.1-blue.svg)| 단위 테스트 프레임 워크 |
| Rombok | ![Generic badge](https://img.shields.io/badge/release-1.18.12-blue.svg)| 모델 데이터 객체 최소화 |
| Nurigo | ![Generic badge](https://img.shields.io/badge/release-2.2.1-blue.svg)| 문자메세지 자동 전송 |
| Ojdbc6 | ![Generic badge](https://img.shields.io/badge/release-11.2.0.1.0-blue.svg)| 오라클 데이터베이스 |
| Tomcat | ![Generic badge](https://img.shields.io/badge/release-9.0.36-blue.svg)| 웹 어플리케이션 서버 |
<br>

## 📜 Data Model Diagram
![KakaoTalk_20200930_153857747](https://user-images.githubusercontent.com/68583697/94651283-2f7f0100-0333-11eb-9093-fcb7d5aafb65.png)
<br>
Member : 사용자 <br>
Class : 개설된 반 <br>
Store : 등록되어 있는 가게 <br>
Waiting : 현재 진행중인 주문 <br>
<br><br>

## 프로젝트 구조
|BackEnd|FrontEnd|
|------|---|
|![캡처](https://user-images.githubusercontent.com/68583697/94669084-c22b9a00-034b-11eb-93e2-a676e2f07368.PNG)|![캡처2](https://user-images.githubusercontent.com/68583697/94669086-c22b9a00-034b-11eb-822b-8852db63051c.PNG)|
<br>



## 💻 최종 실행 화면
<details>
<summary>메인화면</summary>
<div markdown="1">
  
 ![FireShot Capture 012 - chaeyeon - localhost](https://user-images.githubusercontent.com/68583697/94666502-83481500-0348-11eb-80e4-ed9dd1109d22.png)
 
</div>
</details>

<details>
<summary>회원가입 화면</summary>
<div markdown="1">
  
![1](https://user-images.githubusercontent.com/68583697/94666879-ff425d00-0348-11eb-8ad7-4c5402790bd5.png)
 
</div>
</details>

<details>
<summary>모집하기</summary>
<div markdown="1">
  
![2](https://user-images.githubusercontent.com/68583697/94667026-387acd00-0349-11eb-9a6f-2182816f4adc.png)
 
</div>
</details>

<details>
<summary>등록하기</summary>
<div markdown="1">
  
![KakaoTalk_20200930_175651163](https://user-images.githubusercontent.com/68583697/94667114-547e6e80-0349-11eb-8b2e-a91f8b4ac7ef.png)
 
</div>
</details>

<details>
<summary>마이페이지</summary>
<div markdown="1">
  
![KakaoTalk_20200930_175808855](https://user-images.githubusercontent.com/68583697/94667165-65c77b00-0349-11eb-9f0e-81fbea8a85a5.png)

</div>
</details>
<br>

## 🔎 Data Model Diagram - 변경사항 2020-09-08
- 멤버 테이블 마감시간, 수령장소, 최소가격, 최소인원, 상세설명 추가 
- WAIT_MINPERSON 이랑 WAIT_MINLIMIT 이랑 동일 -> WAIT_MINLIMIT 삭제예정 (작업중)
## 🔎 Data Model Diagram - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가

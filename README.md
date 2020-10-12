

# 📁 the-more-the-better
## 🐸 다다익선 🐸
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'
<br><br>
배달료를 공동으로 부담하기 위해 음료를 함께 구매할 수 있도록 일정 인원을 모집해주는 서비스
<br>

## 👩‍💻 팀원 소개
#### • 🧑🏻‍🦰 유재혁(팀장) : QA, 발표 및 자료구성, 깃 프로젝트 관리 
#### • 👩🏻 박주현(Backend) : 회원관리 기능 구현, 주문처리 기능 구현, DB 모델링, 스케줄러 관리
#### • 👦🏻 박준수(Frontend) : API를 통해 주문 화면 구현, 비동기 처리, 주문처리 기능 구현
#### • 👩🏻‍🦰 박채연(Frontend) : SPA 페이지 구성 관리, 크로스 브라우징, 비동기 처리, UI/UX 구현 
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
| 기술 | 버전 | 설명 |
| :--------: | :----: | ---- |
| SpringBoot | ![Generic badge](https://img.shields.io/badge/release-2.3.3-blue.svg) | 스프링 기반 애플리케이션 |
| JPA | ![Generic badge](https://img.shields.io/badge/release-5.4.20-blue.svg) | 관계형 데이터베이스 사용방식 정의 인터페이스 |
| Log4j | ![Generic badge](https://img.shields.io/badge/release-2.13.3-blue.svg) | 프로그램 작성시 로그를 남기기 위해 사용되는 자바 기반 로깅 유틸리티 |
| REST API | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | REST 아키텍처의 제약 조건을 준수하는 애플리케이션 프로그래밍 인터페이스 |
| Oracle RDBMS | ![Generic badge](https://img.shields.io/badge/release-19.3.0.0-blue.svg) | Oracle의 관계형 데이터베이스 관리 시스템 |
| Spring Scheduler | ![Generic badge](https://img.shields.io/badge/release-2.3.2-blue.svg) | 원하는 시간에 원하는 기능을 실행해 주는 Scheduler 라이브러리 |
| MVC Pattern | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | 애플리케이션을 Model, View, Controller 세가지의 역할로 구분한 디자인 패턴 |
| Builder Pattern | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | 복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴 |
| Swagger | ![Generic badge](https://img.shields.io/badge/release-2.8.0-blue.svg) | 팀용 API 문서 및 디자인 도구 |
| JUnit4 | ![Generic badge](https://img.shields.io/badge/release-4.7.1-blue.svg) | 단위 테스트 프레임 워크 |
| Rombok | ![Generic badge](https://img.shields.io/badge/release-1.18.12-blue.svg) | 모델 데이터 객체 최소화 |
| Nurigo | ![Generic badge](https://img.shields.io/badge/release-2.2.1-blue.svg) | 문자메세지 자동 전송 API |
| Vue.js | ![Generic badge](https://img.shields.io/badge/release-4.5.4-blue.svg) | 오픈 소스 프로그레시브 자바스크립트 프론트엔드 프레임워크 |
| ESLint | ![Generic badge](https://img.shields.io/badge/release-6.14.8-blue.svg) | ECMAScript 코드에서 문제점 검사 또는 더 나은 코드로 정정하는 린트 도구 |

<br>

## 📜 ERD (Entity Relationship Diagram)
![KakaoTalk_20200930_153857747](https://user-images.githubusercontent.com/68583697/94651283-2f7f0100-0333-11eb-9093-fcb7d5aafb65.png)
<br>

| Entity | Attributes | Domains |
| :------: | ---------- | ------- |
| MEMBER <br> 회원 | MEM_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> MEM_ID <br> MEM_ISCERTIFY <br> MEM_ISHOST <br> MEM_NAME <br> MEM_PHONE <br> MEM_PW <br> CLASS_NO <br> WAIT_NO |고유 번호 <br> 가입 일 <br> 정보 수정일 <br> 아이디 <br> 인증 여부 <br> 현재 주문 생성 여부 <br> 이름 <br> 전화 번호 <br> 비밀 번호 <br> 소속 반 고유번호 <br> 현재 속한 주문 고유번호 |
| WAITING <br> 현재 진행중인 주문 | WAIT_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> WAIT_CLOSETIME <br> WAIT_PLACE <br> WAIT_MINCOST <br> WAIT_MINPERSON <br> WAIT_STANDBY <br> WAIT_TEXT <br> MEM_NO <br> ST_NO | 고유 번호 <br> 생성 일 <br> 정보 수정일 <br> 마감 시간 <br> 수령 장소 <br> 주문 최소 금액 <br> 주문 최소 인원 <br> 현재 대기인 수 <br> 주문 상세 정보 <br> 주문 대기인 고유 번호 <br> 가게 고유 번호 |
| CLASS <br> 개설된 반 | CLASS_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> CLASS_CODE <br> CLASS_NAME | 고유 번호 <br> 개강 일 <br> 정보 수정일 <br> 고유 코드 <br> 이름 |
| STORE <br> 가게 | ST_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> ST_ADDR <br> ST_NAME <br> ST_PICTURE | 고유 번호 <br> 가입일 <br> 정보 수정일 <br> 상세 주소 <br> 이름 <br> 가게 사진 |

<br><br>

## 🔎 프로젝트 구조

| FrontEnd | BackEnd (main)| BackEnd (test) |
| -------- | ------------- | -------------- |
| ![Screen Shot 2020-10-11 at 9 49 34 PM](https://user-images.githubusercontent.com/64248514/95679127-b2de0380-0c0b-11eb-8a49-c50b1fd676d6.png) | ![Screen Shot 2020-10-11 at 9 44 55 PM](https://user-images.githubusercontent.com/64248514/95679002-1287df00-0c0b-11eb-8141-1c0bd3e5e787.png) | ![Screen Shot 2020-10-11 at 9 25 22 PM](https://user-images.githubusercontent.com/64248514/95678607-b1f7a280-0c08-11eb-8d2c-2856b91e2210.png) |

<br>



## 💻 최종 실행 화면
<img width="1142" alt="finalpage" src="https://user-images.githubusercontent.com/61309080/95737615-edef3e00-0cc2-11eb-8aa7-ae1bb493f209.png">


<br>

## 🔎 ERD - 변경사항 2020-09-08
- 멤버 테이블 마감시간, 수령장소, 최소가격, 최소인원, 상세설명 추가 
- WAIT_MINPERSON 이랑 WAIT_MINLIMIT 이랑 동일 -> WAIT_MINLIMIT 삭제예정 (작업중)
## 🔎 ERD - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가

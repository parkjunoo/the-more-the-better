

# the-more-the-better
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'
## 프로젝트 이름 : 다다익선 
<br>
## 팀원소개
#### • 유재혁(팀장) : QA, 발표 및 자료구성, 프로젝트 관리 
#### • 박주현(Backend) : 회원 가입 및 로그인 구현, 주문처리 기능 구현, DB 모델링
#### • 박준수(Frontend) : 카카오 맵, 비동기 처리, 주문처리 기능 구현
#### • 박채연(Frontend) : Front 디자인, 가게 정보, 비동기 처리, 페이지 구성 관리
<br>
## 프로젝트 설명 :
코로나 바이러스의 창궐로 인한 사회적거리두기가 지속적으로 실시되고 있는 가운데 비대면 언택트 서비스의 중요도가 높아지고 있습니다.
따라서 사람이 여럿 모이는 매장등을 직접 방문하지 않고 배달주문을 통해 매장을 이용할 수 있는 방법을 찾고자 하였습니다.
배달비를 여러 사람들과 함께 나누어 부담을 줄여 여러 사람들의 배달 이용을 활성화 하는 방법을 제시합니다.
### - 학원내 공동 구매 서비스
#### • 음료나 음식을 다른 구매자들과 동시간에 함께 구매하여 배달료를 공동 부담하는 서비스
#### • 음식이나 음료등을 구매하러 직접 매장을 방문할 필요없이 일정 인원을 모아 공동으로 주문하여 배달료와 시간을 절감
#### • 공동구매자를 실시간 모집할 수 있고 모집이 완료된 후 문자서비스를 통해 함께 상품을 주문할 수 있도록 유도 
<br>

## 💻 사용언어, 기술스택
![Generic badge](https://img.shields.io/badge/platform-Web-brightgreen.svg) ![Generic badge](https://img.shields.io/badge/library-vue-blue.svg) ![Generic badge](https://img.shields.io/badge/framework-spring-green.svg)
![Generic badge](https://img.shields.io/badge/database-ORACLE-yellow.svg) ![Generic badge](https://img.shields.io/badge/api-kakaomap-red,.svg) ![Generic badge](https://img.shields.io/badge/language-Java,JavaScript-important.svg)
<br>
## Data Model Diagram
![KakaoTalk_20200930_153857747](https://user-images.githubusercontent.com/68583697/94651283-2f7f0100-0333-11eb-9093-fcb7d5aafb65.png)
<br>
Member : 사용자 <br>
Class : 개설된 반 <br>
Store : 등록되어 있는 가게 <br>
Waiting : 현재 진행중인 주문 <br>
Host : 주문을 연 호스트 <br>
Waiting_Mems : 진행중인 주문의 대기자 모임 <br>
Event : 가게에서 진행중인 이벤트 <br>
<br><br>
## Data Model Diagram - 변경사항 2020-09-08
- 멤버 테이블 마감시간, 수령장소, 최소가격, 최소인원, 상세설명 추가 
- WAIT_MINPERSON 이랑 WAIT_MINLIMIT 이랑 동일 -> WAIT_MINLIMIT 삭제예정 (작업중)
## Data Model Diagram - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가

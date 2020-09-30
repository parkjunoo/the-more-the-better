

# the-more-the-better
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'
## 프로젝트 이름 : 다다익선 

## 팀원소개
#### • 유재혁(팀장) : QA, 발표 및 자료구성, 프로젝트 관리 
#### • 박주현(Backend) : 회원 가입 및 로그인 구현, 주문처리 기능 구현, DB 모델링
#### • 박준수(Frontend) : 카카오 맵, 비동기 처리, 주문처리 기능 구현
#### • 박채연(Frontend) : Front 디자인, 가게 정보, 비동기 처리, 페이지 구성 관리

## 프로젝트 설명 : 
<br>
<br>



## Data Model Diagram
바뀔수 있어요!

![KakaoTalk_20200930_153857747](https://user-images.githubusercontent.com/68583697/94651283-2f7f0100-0333-11eb-9093-fcb7d5aafb65.png)

Member : 사용자 <br>
Class : 개설된 반 <br>
Store : 등록되어 있는 가게 <br>
Waiting : 현재 진행중인 주문 <br>
Host : 주문을 연 호스트 <br>
Waiting_Mems : 진행중인 주문의 대기자 모임 <br>
Event : 가게에서 진행중인 이벤트 <br>

## Data Model Diagram - 변경사항 2020-09-08
- 멤버 테이블 마감시간, 수령장소, 최소가격, 최소인원, 상세설명 추가 
- WAIT_MINPERSON 이랑 WAIT_MINLIMIT 이랑 동일 -> WAIT_MINLIMIT 삭제예정 (작업중)
## Data Model Diagram - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가

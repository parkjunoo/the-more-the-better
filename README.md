

# the-more-the-better
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'
## 프로젝트 이름 : 다다익선 

## 팀원소개
###  유재혁(팀장) : QA, 발표, 프로젝트 관리 
###  박주현(Backend) : oracle, Spring boot, 로그인
###  박준수(Frontend) : 카카오 맵
###  박채연(Frontend) : Front 디자인, 가게 정보



## Data Model Diagram - 변경사항 2020-09-08
- 멤버 테이블 마감시간, 수령장소, 최소가격, 최소인원, 상세설명 추가 
- WAIT_MINPERSON 이랑 WAIT_MINLIMIT 이랑 동일 -> WAIT_MINLIMIT 삭제예정 (작업중)
## Data Model Diagram - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가
## Data Model Diagram
바뀔수 있어요!

![Screen Shot 2020-09-08 at 2 30 56 PM](https://user-images.githubusercontent.com/64248514/92436637-1aa1c880-f1e0-11ea-8252-0c6f5a436b33.png)

Member : 사용자 <br>
Class : 개설된 반 <br>
Store : 등록되어 있는 가게 <br>
Waiting : 현재 진행중인 주문 <br>
Host : 주문을 연 호스트 <br>
Waiting_Mems : 진행중인 주문의 대기자 모임 <br>
Event : 가게에서 진행중인 이벤트 <br>

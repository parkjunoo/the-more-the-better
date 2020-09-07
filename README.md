# the-more-the-better
플레이데이터 Pose-Estimation 개발자 교육 과정 중간 프로젝트 - '다다익선(多多益善)'

## Data Model Diagram - 변경사항 2020-09-07
- 멤버 핸드폰 번호 추가
- 호스트도 waiting_mems 에 들어감
- waiting 테이블에 현재 대기자 인원 추가 

![Screen Shot 2020-09-07 at 12 14 58 PM](https://user-images.githubusercontent.com/64248514/92345362-08525c80-f104-11ea-982f-24932ddcd383.png)

Member : 사용자 <br>
Class : 개설된 반 <br>
Store : 등록되어 있는 가게 <br>
Waiting : 현재 진행중인 주문 <br>
Host : 주문을 연 호스트 <br>
Waiting_Mems : 진행중인 주문의 대기자 모임 <br>
Event : 가게에서 진행중인 이벤트 <br>

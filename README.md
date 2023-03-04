# PPPick 개발 branch

## 📂 프로젝트 소개
- **P**ersonal**P**icture**Pick**의 약자로, 회원이 작가의 포트폴리오를 확인 후, <strong>작가를 선택(PICK)</strong>하여 <br>
  **개인 맞춤형 일러스트/그림/디자인을 제작 의뢰하는 서비스**입니다.
- 최근 개개인의 개성이 중요해지면서 같은 것이라도 남들과는 다름을 추구하는 사람들이 점점 많아지고 있습니다. <br>
  이러한 사람들이 쉽게 접근 할 수 있는 **맞춤형 서비스가 필요**하다고 생각하여 위 프로젝트를 기획하였습니다.
- 프로젝트 GitHub : https://github.com/pickpickpick-project

### 📆 개발 인원 및 기간
- 개발기간 : 2023/01/14 ~ 2023/2/28
- 개발 인원 : 백엔드 3명, 프론트엔드 2명 

## 🙌 팀원소개
역할|이름|GitHub 주소|
---|---|---|
Back-End|김소윤|https://github.com/iiolo
Back-End|김도희|https://github.com/KIM-DO-HEE
Back-End|신승현|https://github.com/SSHTED
Front-End|김호정|https://github.com/HJKim423
Front-End|박정도|https://github.com/jeongdopark

## 🔗 배포 URL
- http://www.pppick.store
- 관리자 페이지 - http://www.pppick.store/admin/login
  - 관리자 계정 - ID : admin / Password : admin 

## 📄 협업 문서
- 요구사항 명세서 : https://docs.google.com/spreadsheets/d/1rjobxqth3hT1llzdAlExbxPFiDr5VKQ4-HNzSaPFTOs/edit#gid=0
- 테이블 명세서 : https://docs.google.com/spreadsheets/d/1g7OMKohGoOJHb5zpbEZkxWEtyvjFE_WmQDpd1fIFgIY/edit#gid=0
- 인스턴스 명세서 : https://docs.google.com/spreadsheets/d/1RTW6A1XULyyLET6QYOyFSAiDhvXmzM52XFMqolsMMrw/edit#gid=0

## 🛠 사용 기술 및 구현 기능
### 사용 기술 및 tools
> - Back-End : <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">&nbsp;![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge)&nbsp;<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

> - Front-End : <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">&nbsp;<img src="https://img.shields.io/badge/reactquery-FF4154?style=for-the-badge&logo=reactquery&logoColor=black">&nbsp;<img src="https://img.shields.io/badge/typescript-3178c6?style=for-the-badge&logo=typescript&logoColor=black">&nbsp;<img src="https://img.shields.io/badge/styledcomponent-DB7093?style=for-the-badge&logo=styledcomponent&logoColor=black">

> - Server : <img src="https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white">&nbsp; <img src="https://img.shields.io/badge/AWS RDS/EC2-232F3E?style=for-the-badge&logo=Amazon&logoColor=white"/>&nbsp;<img src="https://img.shields.io/badge/AWS S3-색상?style=for-the-badge&logo=Amazon S3&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">

> - ETC : <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white">

Swagger 문서 참고 링크 : http://api.pppick.store/swagger-ui/index.html#

### ERD 설계
<img width="80%" height="80%" src="https://user-images.githubusercontent.com/47100801/222034401-12ae32c8-5a9c-40e3-8eb7-e09eab6a1fdd.png">

### 구현 기능
#### User
- Oauth +  Jwt + Spring Security를 사용한 소셜 로그인 구현
- 회원 삭제 시, 관련 게시물, 댓글, 작업물, 상품, 좋아요 등 일괄 삭제 기능
- 회원 정보 수정(프로필 이미지, 소개, 닉네임, 전화번호) 기능
- 회원 탈퇴 기능
- 회원 팔로우

#### Post & Comment
- 게시물 Create, Read, Update, Delete
- 댓글 Create, Read, Update, Delete
- 게시글 작성 시 다중 이미지 파일 업로드 기능
- 회원 당 개인 게시판 생성

#### Portfolio & Tag
- 다중 태그를 통한 작업물 목록 검색
- 포트폴리오 등록, 조회, 삭제
- 포트폴리오 이미지 
- 포트폴리오 좋아요


#### Work & Order
- 상품 Create, Read, Update, Delete
- 작가별 상품 목록 조회
- 상품 이미지 파일 업로드 기능 구현
- 상품 주문 기능 구현
- 회원별 상품 주문 목록 조회 

#### Payment
- 아임포트 API를 연동하여, 상품 결제 기능 구현
- 실제 결제한 정보와 주문정보 검증하는 로직 작성
- 거래 확정이 안된 주문 건에 대하여 환불 버튼 클릭 시, 환불 처리 기능 구현

















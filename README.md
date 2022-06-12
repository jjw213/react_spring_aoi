![Node](https://img.shields.io/badge/node-v14.17.1-blue)
![NPM](https://img.shields.io/badge/npm-v7.19.0-blue)

![React](https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![js](https://img.shields.io/badge/JavaScript-F7DF1E.svg?&style=for-the-badge&logo=JavaScript&logoColor=white)
![css](https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![kakao](https://img.shields.io/badge/Kakao-FFCD00?style=for-the-badge&logo=Kakao&logoColor=white)

![JAVA](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Oracle DB](https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

### **실행을 위해서 [react_spring_frontend](https://github.com/jjw213/react_spring_frontend) 가 필요합니다.**
### 본 레포지토리를 git clone 후 생성된 react_spring_backend > frontend 아래에서 git clone 해주세요.
___

# ORACLE DB TABLE INFO

```c
create table Member2(
    id number(19) PRIMARY Key,
    name VARCHAR2(255),
    password VARCHAR2(255),
    kakao_id number default 0,
    name VARCHAR2(255),
    code VARCHAR2(10),
);

create table board2(
  no number primary key,
  title varchar2(255),
  content varchar2(4000),
  createdate date default sysdate,
  readcount number default 0,
  writer varchar2(255) default 0

);
create table Dibs(
  age varchar2(255), careAddr varchar2(255), careNm varchar2(255),careTel varchar2(255),
        kindcd varchar2(255), desertionNo number, popfile varchar2(255),sexCd varchar2(255),processState varchar2(255),
        specialMark varchar2(255),weight varchar2(255), name varchar2(255), id number PRIMARY key
            );
            
create table REPLY(
    content VARCHAR2(4000), writer VARCHAR2(255), postid number, responseto VARCHAR2(255), commentid VARCHAR2(255) primary key, created_at VARCHAR2(255) 
    default  sysdate,
    exist number default 1
   );
   
CREATE SEQUENCE  MEM_SEQ  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  CYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  BOARD2_SEQ  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  CYCLE  NOKEEP  NOSCALE  GLOBAL ;
create SEQUENCE seq_dibs INCREMENT by 1 START with 1 MINVALUE 0 MAXVALUE 9999 cycle;

commit;
```

# Run

## react_spring_frontend

**개발환경 Visual Studio Code at Windows 10**
1. 터미널에서 git clone https://github.com/jjw213/react_spring_frontend.git
2. node 설치
3. npm i로 node_module 설치
4. 생성된 react_spring_frontend 폴더 바로 밑에 .env 파일 생성 후 kakao developers에서 생성한 앱의 키를 아래 코드에 맞춰서 입력
```c
REACT_APP_FEED_KEY=발급받은 JAVASCRIPT 키
REACT_APP_REST_API_KEY=발급받은 REST API 키
REACT_APP_CLIENT_SECRET=발급받은 CLIENT SECRET 키
```
4. npm start 로 실행


## react_spring_backend

**개발환경 IntelliJ IDEA at Windows 10**
1. 터미널에서 git clone https://github.com/jjw213/react_spring_backend.git
2. build.gradle 설치 후 file > settings > Build, Execution, Deployment > Compiler > Annotation Processors 에서 Enable annotation processing 체크 후 OK
3. Edit Configurations... > Build and run 에서 JAVA 11 버전과 hello.spring.main 설정 후 main class 는 hello.hellospring.HelloSpringApplication로 설정
4. resources 폴더 하위에 **application.properties** 파일 생성 후 아래 코드 작성
```c
#datasource (oracle)
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=오라클 유저 이름
spring.datasource.password=오라클 유저 비밀번호
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true

#mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=구글 이메일
spring.mail.password=구글 앱 키
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.auth=true
```

5. 실행


# setting port

frontend : http://localhost:3000

backend : http://localhost:8080

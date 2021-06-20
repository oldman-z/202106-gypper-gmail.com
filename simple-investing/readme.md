# 개 요
 
* project   : 카카오페이 서버 개발 과제
* framework : Spring Boot 2.4.7
* sdk       : JDK 1.8
* database  : h2 Database

# 프로그램 시작

### 실행
`kakaopay.investing.SimpleInvestingApplication`

### DB 생성 및 기본 자료 등록
최초 실행 시 initialization-mode: always 로 설정하여 DB 를 설정함

    datasource:
      initialization-mode: never
      schema: classpath:h2/schema.sql
      data: classpath:h2/data.sql
      hikari:
        jdbc-url: jdbc:h2:~/testdb
        driver-class-name: org.h2.Driver
        username: sa
        password:

DB 생성 스크립트 참조

     schema: classpath:h2/schema.sql
     data: classpath:h2/data.sql



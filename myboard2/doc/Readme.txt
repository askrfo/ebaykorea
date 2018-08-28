    https://github.com/urstory/ebaykorea
    jpaboard 프로젝트

    examples.boot
    start.spring.io  : myboard 라는 프로젝트를 생성
    web , jpa, h2, devtools, security, thymeleaf, lombok

    domain 패키지를 생성한다.

    Member
    private Long id;  (ID - 자동생성)
    private String name;
    private String email;
    private String passwd;
    private LocalDateTime regdate;

    MemberRole
    private Long id;
    private String name;

    Member <----> MemberRole
           1    *

    Board
    private Long id;
    private String title;
    private String content;
    private LocalDateTime regdate;

    Member <---- Board
           1    *

application.properties --> application.yml 파일로 수정

Datasource에 대한 설정
jpa와 hibernate에 대한 설정 - SQL로그 , 테이블 자동 생성

spring:
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
    username: sa
    password: sa
    initialization-mode: always
  h2:
    console:
      enabled: true
#server:
#  port: 9999

repository 패키지를 생성한다.

JpaRepository<Entity클래스명, Id클래스Type> 를 상속받는

MemberRepository
MemberRoleRepository
BoardRepository

인터페이스를 작성한다.

그리고 나서 resources 폴더에 data.sql 파일을 작성하고

member
member_role
board 테이블에 sample 데이터를 insert하도록 한다.

----------------------------------

비지니스 메소드를 정의한다.

회원 가입
    - 회원 정보가 저장. 이때 회원은 권한 정보도 같이 저장된다.
    - 암호는 암호화 되서 저장된다.

로그인
    - email과 암호를 입력하여 로그인 한다.
    - email을 이용해 회원정보를 조회할 수 있다.

로그아웃

게시물 등록
게시물 목록 보기
    - 페이지에 해당 하는 목록을 읽어온다.(페이지 단위 보기 , 한페이지당 3건)
    - 전체 페이지 수를 구한다. (전체 페이지가 몇인지 구한다.)
게시물 상세 보기
    - id 에 해당하는 게시물 정보를 읽어온다.

---------------------------------------------

service 패키지를 만든다.

2가지 인터페이스를 작성한다.

MemberService
BoardService

2가지 클래스를 작성한다.

MemberServiceImpl 은 MemberService인터페이스를 구현한다.
BoardServiceImpl 은 BoardService인터페이스를 구현한다.

--------------------------------------------------

test 에 servcie 패키지를 만든다.

MemberServiceTest 클래스를 작성한다.

encode : :{bcrypt}$2a$10$GL4F5ni65YNSYSl0SVjfk.p.cAou7WHYNTEUyFefJ8x9.N1CEM4O.
encode : :{bcrypt}$2a$10$G5Qu/l/fe1jQy7yv6aFR1ehNAsUz3lhvtv80ZlJ2i54ansGO2UNWi

myboard2 프로젝트를 ebaykorea 레파지토리에 추가하였어요....
---------------------------------------------------

    @Override
    @Transactional(readOnly = true)
    public Page<Board> getBoards(int page) {
        // 0페이지가 시작 페이지, 3건씩 보여준다.
        PageRequest pageRequest = PageRequest.of(page - 1, 3);
        Page<Board> boardPage = boardRepository.findAllByOrderByIdDesc(pageRequest);
        return boardPage;
    }

 BoardServiceTest 클래스 작성

 data.sql에는 board테이블에 5건 정도 값을 저장.

 getBoards() 테스트 메소드를 작성하여 페이지의 값에 따른 결과가 잘 나오는 지 확인.

----------------------------------------------

controller 패키지를 생성

BoardController 클래스를 만든다.

BoardService를 주입받는다.

/boards 라고 GET 방식요청이 오면
Page<Board> 객체를 ModelMap에 "list"속성으로 저장한다.

"list" view name을 반환하도록 한다.


--------------------------------------------------

resources/templates 아래에 list.html 파일을 작성한다.


spring security가 추가되고 아무설정도 안하면 모든 URL이 로그인 필요하다.

user 계정이 사용되고
암호는

Using generated security password: e99c2d38-df31-49bb-85b4-e31383907f8c

--------------------------------------------------

https://github.com/urstory/ebaykorea

jpaboard 의 소스경로에 보면 config

----------------------------------------

config 패키지를 작성한다.

@Configuration
public class WebApplicationSecurity
            extends WebSecurityConfigurerAdapter{

// 필요한 메소드를 오버라이딩 할 것이다.
}


---------------------------------------

security 패키지를 생성한다.

@Component
public class MyUserDetailService implements UserDetailsService {
 // 구현
}
----------------------------------------

회원 가입 폼.

회원정보 입력 확인(/members/joinform).
    private String name;
    private String email;
    private String passwd;
    joinform.html (thymeleaf 템플릿.)

회원정보가 저장(/members/join, POST방식)
회원정보가 저장된 후 (/members/welcome)

MemberController 에 위의 3가지 path를 처리하는 메소드를 추가.

http://localhost:8080/h2-console
application.yml 에 있는 정보를 이용해서 로그인한다.

form 값 검증.
hibernate validator BindingResult
---------------------------------------

/members/login 이라는 path를 처리하는 컨트롤러 메소드를 만든다.

id와 password 를 입력받는 폼을 만든다. 값은

post방식으로 /members/login으로 전달되게 한다.

--------------------------------------

/boards/writeform  글쓰기 폼이 보여진다.
(로그인한 사용자만 보여져야 한다.)
제목, 내용만 입력하는 폼이 보여진다.

/boards  (POST) 값을 받아서 저장을 한다.
인증정보로 부터 email(login id)을 구한다.
제목, 내용을 입력받는다.

BoardService에게 email, 제목, 내용을 전달하여 저장하도록 한다.

java.security.Principal 를 컨트롤러 메소드에 넣어주면 로그인 id를 구할 수 있다.

BoardServiceImpl과 BoardService를 알맞게 수정한다.
---------------------------------

숙제 : 1) 프로젝트 주제를 정한다.
      2) 프로젝트 주제에 대한 간단한 설명을 작성한다.
      3) 프로젝트에 필요한 엔티티 클래스를 작성한다. (가능하다면 Repository와 Service까지 작성)


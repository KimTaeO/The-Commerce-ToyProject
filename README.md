# The-Commerce ToyProject

## API Docs
[API docs](src/main/resources/static/docs/index.html)
> 서버 실행 시 명세서를 확인할 수 있는 엔드포인트 ```/docs/index.html```

## Commit Convention
|  value   |    meaning    |
|:--------:|:-------------:|
|  create  | 새로운 클래스 추가 시  |
|   add    |  새로운 기능 추가 시  |
|  update  |  변경사항이 생겼을 시  |
|  delete  | 삭제할 사항이 생겼을 시 |
|   fix    |  수정사항이 생겼을 시  |
| refactor |  코드 품질 개선 시   |
|   test   |   테스트 관련 사항    |

## 애플리케이션 실행 방법 가이드

1. 먼저 환경변수들을 설정해줍니다.
    * 스프링 애플리케이션 설정 파일(application.yml)의 환경변수를 할당해줍니다.
      * ```DB_URL``` 연결할 데이터베이스의 URL을 입력합니다. 형식은 ```jdbc:데이터베이스종류://DB의 Host:DB의 Port/데이터베이스 스키마명```
      * ```DB_USERNAME``` 데이터베이스의 사용자명을 입력합니다.
      * ```DB_PASSWORD``` 데이터베이스의 사용자의 비밀번호를 입력합니다.
      * ```DDL_AUTO``` JPA의 DDL 실행 방식을 설정합니다. 4종류가 있습니다 ```create``` 등록된 클래스와 매핑되는 테이블을 자동으로 생성한다. 만약에 테이블이 이미 존재한다면 삭제 후 다시 생성한다, ```create-drop```등록된 클래스와 매핑되는 테이블을 자동으로 생성한다. 애플리케이션 종료 시 테이블을 삭제한다, ```update```등록된 클래스와 매핑되는 테이블이 존재하지 않는다면 자동으로 생성한다 기존 테이블에 컬럼이 새로 추가되면 변경사항을 반영한다, ```validate``` DDL을 실행하여 테이블을 생성하거나 수정하지 않으며, 클래스와 테이블이 제대로 매핑되는지 검증한다.
    * 도커 컴포즈 파일(docker-compose.yml)의 환경변수를 할당해줍니다.
      * ```MYSQL_DATABASE``` 데이터베이스의 자동으로 생성할 스키마 이름을 입력합니다.
      * ```MYSQL_ROOT_PASSWORD``` 데이터베이스 root 사용자의 비밀번호를 입력합니다.
      * 
2. docker compose로 데이터베이스 설치 후 실행
```shell
# 리포지토리 루트 디렉토리로 이동

# docker에서 mysql image pull 후 백그라운드에서 실행  
docker compose up -d
```

3. 프로젝트 빌드
```shell
# 리포지토리 루트 디렉토리로 이동

# 프로젝트 빌드 
./gradlew build
```

4. 프로젝트 실행
```shell
# 리포지토리 루트 디렉토리로 이동

# 프로젝트 실행 (&은 백그라운드 실행)
nohup java -jar /build/libs/The-Commerce-ToyProject-0.0.1-SNAPSHOT.jar &
```
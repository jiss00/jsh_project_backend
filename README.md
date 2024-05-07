# 예약 서비스(Reservation Service) 및 시설평점 업데이트 서비스(Review Score Update Service)  

![java_badge](https://img.shields.io/bage/language-java-yellow)![spring_badge](https://img.shields.io/badge/framework-spring-green)![lombok_badge](https://img.shields.io/badge/library-lombok-blue)![transaction_badge](https://img.shields.io/badge/transactional-true-brightgreen)![schedule_badge](https://img.shields.io/badge/scheduled-true-brightgreen)![logging_badge](https://img.shields.io/badge/logging-slf4j-orange)![redis_badge](https://img.shields.io/badge/database-redis-red)![pagination_badge](https://img.shields.io/badge/pagination-true-ff69b4)  

이 프로젝트는 학교 시설 예약을 처리하고 시설평점을 업데이트하는 데 사용됩니다.  

📅 **기간 업데이트**  
- `@Scheduled` 어노테이션을 사용하여 시설 평점을 업데이트합니다.  
- 평점은 시설 리뷰를 기반으로 매시 정갱신됩니다.

📚 **핵심 기능**  
- **예약 기능:** 사용자는 특정 시설에 대한 예약을 생성하고, 반환, 연장할 수 있습니다.
- **예약 현황 조회:** 사용자는 예약한 시설 및 시간대에 대한 예약 현황을 확인할 수 있습니다.
- **평점 업데이트:** 시설에 대한 평점을 업데이트하여 사용자들에게 정확한 정보를 제공합니다.

## 📅개발기간
2024.01.01~2024.02.25

## 👥팀원소개
### 캡스톤 디자인 팀입니다

1. **이름:** 지승현
   - **학교:** 🏫울산대학교
   - **역할:** 🌐프론트엔드 개발
2. **이름:** 박준혁
   - **학교:** 🏫울산대학교
   - **역할:** 🌐백엔드 개발
3. **이름:** 하태욱
   - **학교:** 🏫울산대학교
   - **역할:** 🌐프론트엔드 개발

## Reservation Service
```java
-예약 서비스는 다음과 같은 주요 기능을 제공합니다:
- 예약 생성, 반환, 연장
- 사용자 예약 내역 조회
- 시설 이용 내역 조회
- 예약 상세정보 조회
```
## Review Score Service
-리뷰 평점 업데이트 서비스는 다음과 같은 주요 기능을 제공합니다:
- 서버 시간으로 매시 평점 업데이트
- 리뷰를 기반으로한 특정 시설 평점 갱신
 위 서비스들은 `Spring 프레임워크`를 기반으로 작성되었으며, `@Scheduled` 어노테이션을 사용하여 주기적인 작업을 처리하고,  
`@Transactional` 어노테이션을 통해 트랜잭션을 관리합니다. 또한 `lombok` 라이브러리로 코드를 간소화하고, `Slf4j`를 사용하여 로깅을 수행합니다.  

🔗 이외에도 `Redis`를 사용하여 검색 로그와 인기 검색어를 관리합니다.  
🌐 둘다 `Java` 기반으로 작성되었으며, 완전한 기능의 예약 서비스와 리뷰 평점 업데이트를 제공합니다.
​

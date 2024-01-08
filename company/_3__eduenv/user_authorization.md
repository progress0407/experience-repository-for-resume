# 사용자 인가 AOP 리펙터링

> 상세한 내용은 담지 않았으며, 실제 내용과는 다른 부분이 존재합니다 

## 배경

아래와 같이 각기 다른 권한의 인가를 표시하는 어노테이션이 존재

```java
@UserAuthorization1
@UserAuthorization2
@UserAuthorization3
@UserAuthorization4
```

각 어노테이션마다 프로세서가 존재하여 대략 8~10개의 코드가 존재

```java
@UserAuthorization(AuthRole.Role1)
```

한 쌍의 어노테이션과 프로세서, Enum으로 1~3개의 코드로 리펙터링

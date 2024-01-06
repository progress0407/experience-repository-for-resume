# Spring Cloud Gateway 라우터 코드 리펙터링 (TDD)

## 문제점

기존의 yaml 파일의 경우 3~6줄 가량의 라우팅 정보(API)가 약 200개, 총 800 줄 정도의 코드가 존재

새로 추가한 API가 라우팅되지 않을 때 어떤 부분에서 문제가 있는 지 빠르게 탐색하기 어려움

또한 코드량 자체가 길기에 한눈에 API 리스트를 파악하기 번거로움



## 방어 코드

```kotlin
fun `route 목록을 검증한다`() {

    val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!

    routes["ITEM-SERVICE"] shouldBe_ Route_("/item", listOf(GET, POST), AuthFilter::class)
    routes["ORDER-SERVICE"] shouldBe_ Route_("/orders")
}
```

### 리펙터링 후 (Kotlin-DSL)

```kotlin
fun `route 목록을 검증한다`() {

    val routes = routes()

    routes["ITEM-SERVICE"] shouldBeRoute {
        path("/items")
        httpMethods(GET, POST)
        filters(AuthFilter::class)
    }

    routes["ORDER-SERVICE"] shouldBeRoute {
        path("/orders")
    }
}

```

## 기존의 코드 (YAML)

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: ITEM-SERVICE
          uri: lb://ITEM-SERVICE
          predicates:
            - Path=/items/**
          filters:
            - RemoveRequestHeader=Cookie
        - id: ORDER-SERVICE
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
            - Method=POST,GET
          filters:
            - RemoveRequestHeader=Cookie
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**
          filters:
            - RemoveRequestHeader=Cookie
```

## 변경 이후의 라우터 코드

```kotlin
@Configuration
class RouteConfig {

    @Bean
    fun routes(builder: RouteLocatorBuilder, loggingFilter: LoggingFilter): RouteLocator {

        return builder.routes()
            .route { it.route("ITEM-SERVICE", "/items") }
            .route { it.route("ORDER-SERVICE", "/orders", httpMethods = arrayOf(POST, GET)) }
            .route { it.route("USER-SERVICE", "/users") }
            .build()
    }
}
```
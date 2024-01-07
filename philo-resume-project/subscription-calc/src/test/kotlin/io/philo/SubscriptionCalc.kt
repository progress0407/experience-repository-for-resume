package io.philo

import io.kotest.core.spec.style.StringSpec

class SubscriptionCalc : StringSpec({

    "Case 1  유저가 현재 구독기간 이전부터 구독한 경우 -> 유저의 구독 시작, 종료일 변경" {
    }

    "Case 2  유저가 현재 구독기간 이전부터 구독을 이어오다가 중도 해지한 경우 -> 회원의 구독 시작일만 변경" {
    }

    "Case 3  유저가 현재 구독기간 이전에 해지한 경우 -> 삭제 (Null 객체 반환)" {
    }

    "Case 4  유저가 현재 구독 기간 도중에 구독한 경우 -> 회원의 구독 종료일만 변경" {
    }

    "Case 5  유저가 현재 구독 기간 도중에 구독하여 중도 해지한 경우 -> 회원의 구독 시작, 종료일 모두 그대로 유지" {
    }

})
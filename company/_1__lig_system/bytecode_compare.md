# 바이트 코드 Compare

## 배경
서버의 자바 코드 디컴파일한 결과물과 로컬 개발환경의 자바 코드를 비교해서 얼마나 많은 코드가 다른 지를 조사하는 일  
당시 맡은 도메인의 자바 파일 개수는 약 2,000개이고 하루 평균 50 ~ 60개의 자바 코드를 비교했다  
File I/O 와 POI를 이용해서 자동화를 한 후 반나절에 처리가 되었다  

## 동작 흐름
- DFS를 통해서 디렉터리 탐색 구현
- 디렉터리 구조를 복사
- 서버와 로컬 코드를 디컴파일(class -> java)한 (by CMD API)
- import, white space 제거
- 파일 하나를 비교후 다른 것이 있다면 full path를 compare 리스트(서로 다른 파일 리스트)에 담음
- 위에서 찾은 디렉터리에 코드가 다른 두 파일을 복제
- compare 리스트를 .txt 파일로 출력

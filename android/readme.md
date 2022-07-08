# Android Airbnb Project

## AirBnb 클론 프로젝트 

주변 위치의 간단한 숙소를 검색 할 수 있는 앱입니다. 사용자는 주변에 가까운 도시까지 몇분만에 갈 수 있는 지 확인할 수 있고, 위치를 선택 한뒤, 예약정보를 입력하면
검색한 위치에 있는 숙박업체의 정보들이 불러와집니다. 

---

## 팀 프로젝트 회의록 

[Team-17 Notion](https://url.kr/we8bp4)
### 팀 프로젝트 회의록 구성
- 스크럼 회의록
- 개발 메모
- 그룹 리뷰
- 일일 회고 

---

## App 구동 화면

|로그인화면|캘린더 선택화면|위치선택화면|
|-------|------------|--------|
|![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/8a0cfa11-7b63-4f60-b9b3-1b204df7dafb/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220707%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220707T054714Z&X-Amz-Expires=86400&X-Amz-Signature=f18ed2239a97770b3a92b29cd1876e351944be4146f0e96c5917b686266f7caf&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject) | ![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/47055665-6151-4627-8fe7-ca2ea9c46302/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220707%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220707T055206Z&X-Amz-Expires=86400&X-Amz-Signature=b676c9a8de80c028a9d9c6f2d404f1545df717a28e6f2fca70c1ed9318800554&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject) | ![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bf13f0b6-8e1d-48c8-8935-8cbc57765cd9/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220707%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220707T055225Z&X-Amz-Expires=86400&X-Amz-Signature=8eda87f82d3b6567f9dcbb966531a3b184ad7a9a2e528b950e6568b8f5da18e4&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

---

## 사용된 기술들

### 기본 라이브러리
- Android KTX
- AppCompat

### Network
- Okhttp3
- Retrofit 2

### Jetpack AAC
- Data Binding
- Lifecycles
- ViewModel

### DI
- Hilt

### Asynchronous Process
- Flow

### UI 
- Material 디자인 

## Architecture - MVVM
- MVVM 구조를 선택하였습니다.
![Image](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?hl=ko)

---

## 기술적 도전

---
### Date Picker 커스터마이징
- 기존에 존재하는 Material Design 의 Date Picker 을 커스터 마이징 하기 위해서 직접 모든 클래스를 가져와 Fragment 로 직접 작성하였습니다.
- 기존 Date Picker 에서 제공하는 버튼들을 커스터 마이징 해서 다른 Vector Asset으로 지정 및 위치를 지정해 주었습니다.

---
### Flow 활용
- Flow 를 선택하여 계층간 데이터 전달과 비동기 이벤트를 처리하였습니다. 이를 통해, 코드의 일관성을 높이려 노력하였습니다. 
- Coroutine 으로 TMAP API 에서 가져오는 데이터를 순서에 맞게 가져오는데 많은 시간이 걸렸습니다. Suspend 함수를 이용하는 Coroutine 의 
특성에 따라 데이터 요청을 순서대로 받기 위해서는 각각의 Job 의 역할이 끝날때까지 기다리는 Join 함수를 계속 호출해 줄 수 밖에 없었습니다. Flow 를 
활용하여, 순차적으로 데이터를 요청하고, Join 함수를 제거할 수 있었습니다.

---
### MVVM 및 클린 아키텍쳐
- 비즈니스 로직이 변경될 가능성이 큰 데이터 레이어, UI레이어로 구분하여 결합도를 낮추기 위해서 MVVM 패턴을 채택하였습니다.
- ViewModel과 View의 역할을 나누는 작업을 리펙토링 과정에서 많이 수정하였으며, View는 유저에게 UI를 보여주는 역할만, ViewModel은 로직을 담는
역할을 적절히 분배하려 노력하였고, 이를 통해서 역할분리를 나누었습니다. 

---

## 팀 구성원

### Backend

- 반스 (https://github.com/ffinn92)
- 피오 (https://github.com/NB993)

### Android

- Josh (https://github.com/junseokseo9306))
- Wooki (https://github.com/banjjak2)

---
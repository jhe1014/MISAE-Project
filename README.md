﻿# MISAE-Project

 * 공공api를 이용한 미세먼지 수치 제공 어플리케이션
 
  미세먼지를 포함한 대기 정보를 제공하며 그밖에 기상정보와 기상청 인증 마스크 목록, 개인이 측정한 수치를 기록받는 기능이 있다.

▶ 프로그램 주요 특성

 *nodejs를 통해 어플리케이션과 MySQL간의 데이터 교환이 가능하도록 한다.
 *어플리케이션을 통해 사용자가 개인적으로 측정한 수치값을 FireBase에 저장한다.
 *MySQL에서 공공api를 통해 데이터를 받는다.

## 미세먼지 

LiveAtmosphere

 - 실시간 미세먼지 수치를 호출하는 클래스

ForecastAtmosphere

- 미세먼지 예보 수치를 호출하는 클래스

## 날씨

LiveWeather

 - 실시간 날씨 정보 호출 클래스

LiveForecastWeather

 - 초단기예보 날씨 정보 호출 클래스

ForecastWeather

 - 날씨 예보 정보 호출 클래스
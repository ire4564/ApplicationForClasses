

![앗! 벌써 수강신청이잖아_ (1)](https://user-images.githubusercontent.com/44183221/131077018-747ff067-4941-4806-8355-1ef80c690bed.png) 


![preview badge](https://img.shields.io/github/languages/code-size/ire4564/Application_for_classes)  ![preview badge](https://img.shields.io/github/repo-size/ire4564/Application_for_classes)

## Tech Stack

<img src="https://img.shields.io/badge/Android Native-00c7b7?style=flat&logo=androidstudio&logoColor=white"/>  [![img](https://camo.githubusercontent.com/830fa8a3e043db4dfec789f35fc944fee18f4ab5c69270ee7a1153164e6d3ead/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176612d3135373262363f7374796c653d666c6174266c6f676f3d63737333266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/830fa8a3e043db4dfec789f35fc944fee18f4ab5c69270ee7a1153164e6d3ead/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176612d3135373262363f7374796c653d666c6174266c6f676f3d63737333266c6f676f436f6c6f723d7768697465)  [![img](https://camo.githubusercontent.com/937e9c5148840b2a524cc88a9a5be2acee19641a8f1f167bc84a981ced06c82f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f50686f746f73686f702d3030353263633f7374796c653d666c6174266c6f676f3d61646f6265266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/937e9c5148840b2a524cc88a9a5be2acee19641a8f1f167bc84a981ced06c82f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f50686f746f73686f702d3030353263633f7374796c653d666c6174266c6f676f3d61646f6265266c6f676f436f6c6f723d7768697465)  

## Video Demo 
![gif](https://user-images.githubusercontent.com/44183221/131077065-59925e1e-1ff8-4f7a-9cec-2c908f6be3d3.gif) ![gif (2)](https://user-images.githubusercontent.com/44183221/131077073-1e76119f-cba6-4450-a99e-4b3c6c2788fd.gif) 


## About Project

매번 학기 시작 전에 시간표를 짜야 하는 번거로움과, 시간표를 짜는 시간 투자 대비 비효율적인 시간표가 나오게 되는 경우가 빈번하다고 생각하였습니다. 이에 따라 공강 시간 활용 부족, 시간표에 대한 경우의 수 파악 불가 문제를 해결하고자 프로젝트를 시작하였습니다. 

본 프로젝트의 Application에서는 개인의 상황에 맞는 조건 (공강 요일 설정, 시간표 시간대 설정 등)을 수집하여 개인에게 최적화 된 시간표를 제공합니다.



## Point function

* <b>화면에 따른 개인정보 수집</b>
  자신의 학년, 원하는 이수 학점, 필수로 들어야 할 항목에 대해 수집합니다.
* <b>공강 요일 설정</b>
  원하는 공강 요일을 선택합니다. 단, 조건에 대하여 가능할 경우만 공강이 만들어 질 수 있습니다.
* <b>시간표 시간대 설정</b>
  오전, 오후, 상관없음의 3가지 선택지를 통하여 자신이 편한 시간대를 고릅니다.
* <b>시간표 3개 제안</b>
  위의 수집한 정보를 바탕으로 개인이 원하는 조건에 맞는 시간표의 대안을 3개 제시합니다. 제시된 시간표를 아래의  <수정> 버튼을 통하여 개인이 원하는 맞춤 시간표를 만들 수 있습니다.
* <b>제안된 시간표 수정 기능</b>
  제시된 시간표를 보고, 자신이 원하는 것과 가장 유사한 대안 시간표를 고른 뒤, 시간표 <수정> 또는 <삭제> 기능을 통하여 시간표를 조정합니다.

> <b>시간표 알고리즘</b>
>
> 학년과 필수적인 과목을 입력받은 후, 해당하는 과목들을 공강, 선호하는 시간대 조건을 거치도록 합니다. 다음으로 남은 과목들끼리 <완전탐색>을 하여 겹치는 시간이 없는 목록을 만든 후, 남은 학점 만큼 자동으로 추가 교양 과목을 넣도록 합니다. 이 동작을 할 때에도 역시 시간표 중복 여부에 따라 완전탐색으로 조건을 파악하여 시간표에 추가하도록 하였습니다.



## My Position

>  Writer: <b>ire4564</b> 

<span><img src="https://img.shields.io/badge/Android Native Developer-00c7b7?style=flat&logo=androidstudio&logoColor=white"/></span>  <img src="https://img.shields.io/badge/UI/UX Design-cc6699?style=flat&logo=adobe&logoColor=white"/>

구현 Application의 동작 화면을 구성하는 <b>Front-end</b>를 담당하였습니다.

정보를 처리하기에 앞서, 사용자의 정보를 우선 수집해야 합니다. 그에 필요한 액티비티들을 UX를 고려하여 설계하고 구현하였습니다. 수집할 정보에 따른 사용자의 정보 입력을 유도하기 위해 버튼과 안내문구, 적절한 아이콘을 디자인하고 그에 따른 UI를 XML코드를 이용하여 구현하였습니다.

이렇게 구현된 화면단을 기초로, Activity안에 있는 요소들에 대한 동작을 구현하였습니다. 
사용자가 원하는 정보의 버튼을 누를 경우, 그 의도에 맞는 정보를 intent를 사용하여 다음 화면으로 정보를 넘겨주도록 하고, 원하는 정보를 모두 담아 최종 화면으로 넘겨주어 시간표 작성 알고리즘이 원활하게 동작 되도록 구현하였습니다.  이러한 정보들은 SharedPreperence를 이용하여 앱 자체 내에 데이터가 남아있도록 처리하였습니다. 예외 처리 상황, 사용자가 선택지를 잘못 선택하여 뒤로 돌아가는 동작 등 <사용자의 경험과 시나리오>를 염두에 두며 기능을 개발하였습니다.



## Overview

아래의 화면은 실제 전공 과목을 이용하여 들을 과목을 선택하고, 학점을 선택할 수 있도록 동작하면 화면을 설계한 모습입니다. 이외에 9개의 동작화면이 있으며, 각 화면에 대한 자세한 설명과 내용은 아래의 Detail을 확인해주세요.

![image](https://user-images.githubusercontent.com/44183221/86327968-122c8f00-bc7f-11ea-9720-084ee2ac1e2b.png) 



## Detail 

![image](https://user-images.githubusercontent.com/44183221/86326980-72223600-bc7d-11ea-804b-4610b81b5bac.png) 


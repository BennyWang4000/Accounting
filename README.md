## Accounting App
###### This is a app that take "Ahorro" as reference for my summer vacation homework 
###### Not done yet :D

-------
#### 2020/08/16
###### Done:
- A calculator in "AddNewActivity" via bottom sheet is working
- Calculator is working normally
- Color and UI updated (button background...)
###### To do:
- Fix number type in calculator 
- Fix the view pager/radio group problem
- Remake a new date picker
- Use theme to control most color of item
- A splash screen
- App launch icon
- Setting menu
- Update database for analysis
- Item type
-------
#### 2020/08/11
###### Done:
- Pie chart
- Navigation drawer
###### To do:
- Fix the view pager/radio group problem
- Update UI
- Fix "No chart data available."
-------
#### 2020/07/30
###### Done:
- date picker dialog is showed with correct date
- SelectedDate in repository is work correctly with companion object
- changes the fragment when user changes the date
###### To do
- banner view pager
- view pager 2
- navigation drawer
- navigation bottom
- prettify UI
- Android Architecture Components samples
-------
###### 8/6 目前問題 
- **view pager 日期會重複**
- navigation 
-------



找個時間製作此 app 的類別圖和流程圖
    - 重構各大 fragment 和 activity
    
    為配合 navigation drawer 和各功能將 main activity 改成 fragment 互換
    
    理想：可以用 recycler view 中的 position 選擇 table 中的 id
------
方案：
     1. 一個 database 一個 table ，全部塞進同一個資料表
         -目前使用
         -認知中會造成多餘的查詢
         -最好就是指查日期
     2. 一個 database ??個 table ，新增資料時，若沒有以該日期命名之資料表，則建立並放進資料
         -採用 : ALTER TABLE *** RENAME TO $date / (:data)
         -問題 : An annotation argument must be a compile-time constant
         -問題 : 反正就一堆問題
     3. 一個 database 一個 table 包著??個 table，兩個欄位：一個日期、一個是放 item 的資料表
         -巢狀資料表可以ㄇ 🤔🤔🤔

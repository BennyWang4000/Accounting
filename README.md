## Accounting App
###### This is a app that take "Ahorro" as reference for my summer vacation homework 
###### Not done yet :D
-------
#### 2020/08/18
###### New ER of Table:
![ER](https://cdn.discordapp.com/attachments/744849098926063667/745182833617928232/unknown.png)
###### To do: 
- rebuild current table :D 

-------
#### 2020/08/16
###### Done:
- A calculator in "AddNewActivity" via bottom sheet is working
- Calculator is working normally
- Color and UI updated (button background...)
###### To do:
- *Ready for rebuild database*
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
------
#### 資料庫優化：
###### 一個 database 一個 table ，全部塞進同一個資料表
- 目前使用
- 認知中會造成多餘的查詢
- 最好就是指查日期 :D
###### 一個 database ??個 table ，新增資料時，若沒有以該日期命名之資料表，則建立並放進資料
- 採用 : ALTER TABLE *** RENAME TO $date / (:data)
- 問題 : An annotation argument must be a compile-time constant
- 問題 : 反正就一堆問題
###### 一個 database 一個 table 包著??個 table，兩個欄位：一個日期、一個是放 item 的資料表
- 巢狀資料表可以ㄇ 🤔🤔🤔

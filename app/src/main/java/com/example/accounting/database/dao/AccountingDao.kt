package com.example.accounting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.accounting.database.model.*

@Dao
interface AccountingDao {

    //expense_table--------------------------------------
    @Query("SELECT * FROM expense_table")
    fun getAllExpenses(): LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table WHERE date LIKE :m")
    fun getMonthlyExpenses(m: String): LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table WHERE date IN (:d)")
    fun getDailyExpenses(d: String): LiveData<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: ExpenseEntity)

    //category_table--------------------------------------
    @Query("SELECT * FROM  category_table")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE _id IN (:id)")
    fun getCategories(id: Int): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE name IN (:n)")
    fun getTypeId(n: String): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: CategoryEntity)

    @Query("DELETE FROM category_table WHERE _id IN (:id)")
    fun delCategory(id: Int)

    //account_table--------------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAccount(account: AccountEntity)
//    @Query("INSERT INTO account_table VALUES (:i, :n)")
//    fun insertAccount(i: Int, n: String)

    //routine_table--------------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRoutine(routine: RoutineEntity)

    //setting_table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSetting(setting: SettingsEntity)

    @Query("SELECT * FROM setting_table WHERE _id = 1")
    fun getSettings(): LiveData<List<SettingsEntity>>
    @Query("SELECT budget_amount FROM setting_table WHERE _id= 1")
    fun getBudgetAmount(): LiveData<Int>
    @Query("UPDATE setting_table SET default_account_index = (:index) WHERE _id = 1")
    fun updateDefaultAccountIndex(index: Int)

    @Query("UPDATE setting_table SET budget_amount = (:am) WHERE _id = 1")
    fun updateBudgetAmount(am: Int)

    @Query("UPDATE setting_table SET tracker_id = (:id) WHERE _id = 1")
    fun updateTrackerId(id: String)
    @Query("UPDATE setting_table SET budget_show = (:isShow) WHERE _id = 1")
    fun updateBudgetShow(isShow: Boolean)
    @Query("UPDATE setting_table SET is_first_launch = (:isFirst) WHERE _id = 1")
    fun updateIsFirstLaunch(isFirst: Boolean)
    @Query("UPDATE setting_table SET currency_code = (:code) WHERE _id = 1")
    fun updateCurrencyCode(code: Int)
    @Query("UPDATE setting_table SET budget_start_date = (:date) WHERE _id = 1")
    fun updateBudgetStartDate(date: Int)
    @Query("UPDATE setting_table SET passcode = (:code) WHERE _id = 1")
    fun updatePasscode(code: Int)

}


package com.breckneck.mysecondprojectmoneybox.data.migration

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.breckneck.mysecondprojectmoneybox.data.database.GoalDatabase
import com.breckneck.mysecondprojectmoneybox.data.entity.Goal
import com.breckneck.mysecondprojectmoneybox.data.storage.MigrationStorage

private val GOALS_LIST_MEMORY = "goals list memory"
private val FIRST_ITEM = "firstitem"
private val FIRST_COST = "firstcost"
private val FIRST_MONEY = "firstmoney"
private val SECOND_ITEM = "seconditem"
private val SECOND_COST = "secondcost"
private val SECOND_MONEY = "secondmoney"
private val THIRD_ITEM = "thirditem"
private val THIRD_COST = "thirdcost"
private val THIRD_MONEY = "thirdmoney"

private val APP_PREFERENCES = "settings"
private val APP_PREFERENCES_ITEM = "item"
private val APP_PREFERENCES_MONEY = "money"
private val APP_PREFERENCES_COST = "cost"
private val APP_PREFERENCES_GOAL_ID = "goalid"

class MigrationStorageImpl(val context: Context): MigrationStorage {

    val db = Room.databaseBuilder(context, GoalDatabase::class.java, "GoalDatabase").build()
    val listGoals = context.getSharedPreferences(GOALS_LIST_MEMORY, Context.MODE_PRIVATE)
    val appSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun migration() {
        if (!appSettings.contains(APP_PREFERENCES_GOAL_ID)) return

        val currentGoalId = appSettings.getInt(APP_PREFERENCES_GOAL_ID, 0)
        val goals = when (currentGoalId) {
            1 -> listOfNotNull(
                readGoal(appSettings, 1, APP_PREFERENCES_ITEM, APP_PREFERENCES_COST, APP_PREFERENCES_MONEY),
                readGoal(listGoals, 2, SECOND_ITEM, SECOND_COST, SECOND_MONEY),
                readGoal(listGoals, 3, THIRD_ITEM, THIRD_COST, THIRD_MONEY)
            )
            2 -> listOfNotNull(
                readGoal(listGoals, 1, FIRST_ITEM, FIRST_COST, FIRST_MONEY),
                readGoal(appSettings, 2, APP_PREFERENCES_ITEM, APP_PREFERENCES_COST, APP_PREFERENCES_MONEY),
                readGoal(listGoals, 3, THIRD_ITEM, THIRD_COST, THIRD_MONEY)
            )
            3 -> listOfNotNull(
                readGoal(listGoals, 1, FIRST_ITEM, FIRST_COST, FIRST_MONEY),
                readGoal(listGoals, 2, SECOND_ITEM, SECOND_COST, SECOND_MONEY),
                readGoal(appSettings, 3, APP_PREFERENCES_ITEM, APP_PREFERENCES_COST, APP_PREFERENCES_MONEY)
            )
            else -> emptyList()
        }

        db.runInTransaction(Runnable {
            val dao = db.appDao()
            goals.forEach { goal -> dao.insertGoal(goal) }
        })
    }

    private fun readGoal(
        preferences: SharedPreferences,
        id: Int,
        itemKey: String,
        costKey: String,
        moneyKey: String
    ): Goal? {
        if (!preferences.contains(itemKey) || !preferences.contains(costKey) || !preferences.contains(moneyKey)) {
            return null
        }

        return Goal(
            id = id,
            cost = preferences.getFloat(costKey, 0F).toDouble(),
            money = preferences.getFloat(moneyKey, 0F).toDouble(),
            item = preferences.getString(itemKey, "").orEmpty()
        )
    }
}
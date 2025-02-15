package com.logbook.snackstats.data.db

//@Database(entities = [MealLog::class, MealType::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun mealLogDao(): MealLogDao
//    abstract fun mealTypeDao(): MealTypeDao
//
//    companion object {
//        @Volatile private var instance: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return instance ?: synchronized(this) {
//                val db = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "food_log_db"
//                ).build()
//                instance = db
//                db
//            }
//        }
//    }
//}

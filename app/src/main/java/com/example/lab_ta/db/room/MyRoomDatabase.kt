package bd.com.ratehammer.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab_ta.db.room.converter.Converter
import bd.com.ratehammer.models.fields.ModelFields
import bd.com.ratehammer.models.legals.ModelLegals
import bd.com.ratehammer.models.locales.ModelLocales
import bd.com.ratehammer.models.versions.DataVersions

@Database(
    entities = [
        DataVersions::class, ModelLocales::class, ModelLegals::class, ModelFields::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun dataVersionDao(): DataVersionDao
    abstract fun localesDao(): LocalesDao
    abstract fun legalsDao(): LegalsDao
    abstract fun fieldsDao(): FieldsDao

    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null
        fun getInstance(context: Context): MyRoomDatabase {
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
            INSTANCE?.openHelper?.writableDatabase
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MyRoomDatabase::class.java, "rate_hammer_bd"
        )
            .addMigrations()
            .build()
    }

}
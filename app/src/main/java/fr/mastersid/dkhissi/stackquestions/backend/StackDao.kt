package fr.mastersid.dkhissi.stackquestions.backend

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mastersid.dkhissi.stackquestions.objects.Question
import java.util.concurrent.Flow
import javax.inject.Singleton


@Dao
interface StackDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAll ( list : List < Question >)

    @Query(" SELECT * FROM question_table ")
    fun getQuestionsList () : kotlinx.coroutines.flow.Flow<List<Question>>
}


@Database(
    entities = [ Question::class ],
    version = 1,
    exportSchema = false
)
abstract class StackRoomDatabase : RoomDatabase() {
    abstract fun stackDao () : StackDao
}


@InstallIn( SingletonComponent::class )
@Module
object StackRoomDatabaseModule {
    @Provides
    @Singleton
    fun provideStackDao(stackRoomDadabase: StackRoomDatabase): StackDao {
        return stackRoomDadabase.stackDao()
    }

    @Provides
    @Singleton
    fun provideStackRoomDatabase(@ApplicationContext appContext: Context): StackRoomDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            StackRoomDatabase::class.java,
            "question_table"
        ).build()
    }

}
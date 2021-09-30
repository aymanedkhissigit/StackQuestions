package fr.mastersid.dkhissi.stackquestions.backend


import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.dkhissi.stackquestions.objects.Question
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


private const val BASE_URL = "https://api.stackexchange.com/2.3/"
interface StackWebservice {

    @GET("questions?")
   suspend fun getQuestionsList (
        @Query("pagesize") pagesize : Int = 20,
        @Query ("order") order : String="desc",
        @Query ("sort") sort : String="activity",
        @Query ("site") site: String = "stackoverflow"): List<Question>
}

@Module
@InstallIn( SingletonComponent::class )
object StackWebserviceModule {

    @Provides
    fun provideStackWebservice ( retrofit : Retrofit): StackWebservice {
        return retrofit.create ( StackWebservice::class.java )
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi : Moshi) : Retrofit {
        return Retrofit.Builder ()
            . addConverterFactory (MoshiConverterFactory.create(moshi))
            . baseUrl (BASE_URL)
            . build()
    }

    @Provides
    @Singleton
    fun provideMoshi():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(QuestionsMoshiAdapter())
            .build()
    }

}

/*data class ListQuestionsJsonh (
    val list : List < WeatherJson >
)
data class WeatherJson (
    val name : String ,
    val main : MainJson
)
data class MainJson (
    val temp : Float
)*/


data class ListQuestionsJson ( val items : List<ItemsJson>)

data class ItemsJson (val title : String, val answer_count : Int)

class QuestionsMoshiAdapter {
    @FromJson
    fun fromJson ( listQuestionsJson: ListQuestionsJson ) :List<Question>{
        return  listQuestionsJson.items.map {
            itemsJson ->  Question(itemsJson.title , itemsJson.answer_count)
        }
    }

}
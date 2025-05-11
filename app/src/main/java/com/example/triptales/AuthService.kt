import com.example.triptales.ApiResponse
import com.example.triptales.model.Utente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/signup/")
    fun signup(@Body utente: Utente): Call<ApiResponse>

    @POST("api/login/")
    fun login(@Body utente: Utente): Call<ApiResponse>
}

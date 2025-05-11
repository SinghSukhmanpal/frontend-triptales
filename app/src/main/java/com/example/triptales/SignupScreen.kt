package com.example.triptales

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.triptales.model.Utente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignupScreen(navToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Button(onClick = {
            val utente = Utente(username, password)
            RetrofitClient.authService.signup(utente).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    val msg = response.body()?.message ?: response.body()?.error ?: "Errore"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    if (response.isSuccessful) {
                        navToLogin()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(context, "Errore di rete", Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text("Sign Up")
        }
        TextButton(onClick = navToLogin) {
            Text("Hai già un account? Login")
        }
    }
}

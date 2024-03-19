package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import javax.crypto.SecretKey
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewDecryptedData() {
    val encryptedDataFromJavaScript = "Fs2YlPbeWSLnYCYY5MitxXAKznr9cd1m7qdZAZr7D4w=".toByteArray()
    val decryptedData = aesDecrypt(Base64.getDecoder().decode(encryptedDataFromJavaScript))
        Text("Original Data: ${String(decryptedData)}")
}

fun aesDecrypt(encryptedData: ByteArray): ByteArray {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val ivParameterSpec = IvParameterSpec(ByteArray(16))
    val secretKeyString = "0123456789abcdef0123456789abcdef"
    val secretKey: SecretKey = SecretKeySpec(secretKeyString.toByteArray(), "AES")
    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
    return cipher.doFinal(encryptedData)
}


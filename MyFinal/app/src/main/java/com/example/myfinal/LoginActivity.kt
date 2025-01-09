package com.example.myfinal

// LoginActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 初始化資料庫
        dbHelper = DatabaseHelper(this)

        // 初始化視圖元件
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        loginButton = findViewById(R.id.loginButton)

        // 設置登入按鈕點擊事件
        loginButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            when {
                name.isEmpty() -> {
                    nameEditText.error = "請輸入姓名"
                    return@setOnClickListener
                }
                phone.isEmpty() -> {
                    phoneEditText.error = "請輸入電話"
                    return@setOnClickListener
                }
                else -> {
                    // 儲存使用者資訊
                    val userId = dbHelper.insertUser(name, phone)
                    if (userId != -1L) {
                        Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show()
                        // 跳轉到主頁面
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "登入失敗，請重試", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
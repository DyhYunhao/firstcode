package com.dyh.firistcode.broadcast

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.dyh.firistcode.BaseActivity
import com.dyh.firistcode.MainActivity
import com.dyh.firistcode.R

class LoginActivity : BaseActivity() {

    lateinit var account: EditText
    lateinit var password: EditText
    lateinit var mCbRememberPwd: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        account = findViewById<EditText>(R.id.et_account)
        password = findViewById<EditText>(R.id.et_password)
        mCbRememberPwd = findViewById(R.id.cb_remember_pwd)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            val account1 = prefs.getString("account", "")
            val password1 = prefs.getString("password", "")
            account.setText(account1)
            password.setText(password1)
            mCbRememberPwd.isChecked = true
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val account = account.text.toString()
            val password = password.text.toString()
            if (account == "admin" && password == "123456") {

                val editor = prefs.edit()
                if (mCbRememberPwd.isChecked) {
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
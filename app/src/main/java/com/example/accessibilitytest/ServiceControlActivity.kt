package com.example.accessibilitytest

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.accessibilitytest.accessibility.CustomAccessibilityService
import com.example.accessibilitytest.databinding.ActivityServiceControlBinding

class ServiceControlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceControlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceControlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUi()
    }

    override fun onResume() {
        super.onResume()
        updateAccessibilityServiceStatus()
    }

    private fun updateAccessibilityServiceStatus() {
        updateAccessibilityServiceStatusUi(isAccessibilityServiceEnabled())
    }

    private fun initUi() {
        updateAccessibilityServiceStatusUi(isAccessibilityServiceEnabled())
        binding.btnAccessibilityService.setOnClickListener {
            openAccessibilityServiceSettings()
        }
    }


    private fun openAccessibilityServiceSettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun updateAccessibilityServiceStatusUi(status: Boolean) {
        binding.btnAccessibilityService.text =
            if (status) getString(R.string.accessibility_service_btn_text_off) else getString(R.string.accessibility_service_btn_text_on)
        binding.tvAccessibilityServiceStatus.text =
            if (status) getString(R.string.accessibility_service_on) else getString(R.string.accessibility_service_off)
    }


    private fun isAccessibilityServiceEnabled(): Boolean {
        val enabledServices: String? = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        enabledServices?.let{
            val colonSplitter = TextUtils.SimpleStringSplitter(':')
            colonSplitter.setString(enabledServices)
            while (colonSplitter.hasNext()) {
                val componentName = colonSplitter.next()
                if (componentName.equals(
                        "${packageName}/${CustomAccessibilityService::class.java.name}",
                        ignoreCase = true
                    )
                ) {
                    return true
                }
            }
        }
        return false
    }

}
package com.antsfamily.sellersblogreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.antsfamily.sellersblogreader.navigation.Navigation
import com.antsfamily.sellersblogreader.ui.theme.SellersBlogReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SellersBlogReaderTheme {
                Navigation()
            }
        }
    }
}

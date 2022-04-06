package com.example.carshowroom.screen.auth.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.carshowroom.screen.auth.view.ui.LoginView
import org.koin.androidx.compose.getViewModel

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginView(getViewModel())
            }
        }
    }
}

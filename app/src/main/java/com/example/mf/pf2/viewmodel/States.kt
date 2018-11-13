package com.example.mf.pf2.viewmodel

import com.example.mf.pf2.Spendings

sealed class SpendingsState {
    class Success(val spendings: List<Spendings>) : SpendingsState()
    class Failed(val message: String?) : SpendingsState()
}

sealed class PostSpendingsState {
    class Success : PostSpendingsState()
    class Failed(val message: String?) : PostSpendingsState()
}
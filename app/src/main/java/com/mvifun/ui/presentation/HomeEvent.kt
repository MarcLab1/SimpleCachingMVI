package com.mvifun.ui.presentation

sealed class HomeEvent()
{
    data class OnPress(val id: Int) : HomeEvent()
    data class OnLongPress(val id : Int) : HomeEvent()
    data class OnDeleteClick(val id : Int) : HomeEvent()
}

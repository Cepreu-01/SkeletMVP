package com.example.skeletmvp.ui.view.fragments.main.presenter

import android.os.Bundle
import com.example.skeletmvp.repository.room.model.UserWithAddress

interface IMainPresenter {
    fun observeChanges()
    fun destroyRefs()
}
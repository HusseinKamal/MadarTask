package com.hussein.madartask.di

import androidx.room.Room
import com.hussein.madartask.data.database.AppDatabase
import com.hussein.madartask.data.database.Util.DATABASE_NAME
import com.hussein.madartask.domain.use_case.ValidateAge
import com.hussein.madartask.domain.use_case.ValidateGender
import com.hussein.madartask.domain.use_case.ValidateJobTitle
import com.hussein.madartask.domain.use_case.ValidateName
import com.hussein.madartask.presentation.user.UserViewModel
import com.hussein.madartask.presentation.home.UserProfileModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    factory { ValidateName() }
    factory { ValidateAge() }  // Similarly for other validators
    factory { ValidateJobTitle() }
    factory { ValidateGender() }
    single {
        get<AppDatabase>().userDao() // Assuming you have your Room AppDatabase set up.
    }

    viewModel {
        UserViewModel(
            userDao = get(), // Get the UserDao instance.
            validateName = get(),
            validateAge = get(),
            validateJobTitle = get(),
            validateGender = get()
        )
    }
    viewModelOf(::UserProfileModel)
}
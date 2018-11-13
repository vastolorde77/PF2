package com.example.mf.pf2.di.components

import com.example.mf.pf2.di.SpendingsScope
import com.example.mf.pf2.di.modules.SpendingsListModule
import com.example.mf.pf2.ui.activities.AddSpendingsActivity
import com.example.mf.pf2.ui.fragments.SpendingsListFragment
import dagger.Subcomponent

@SpendingsScope
@Subcomponent(
        modules = [SpendingsListModule::class, SpendingsListModule.ProvideViewModel::class]
)
interface SpendingsComponent {
    fun inject(spendingsListFragment: SpendingsListFragment)
    fun inject(addSpendingsActivity: AddSpendingsActivity)
}
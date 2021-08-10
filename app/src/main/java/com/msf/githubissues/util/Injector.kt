package com.msf.githubissues.util

import com.msf.githubissues.presenter.IssuesPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface Injector {
    fun inject(issuesPresenter: IssuesPresenter)

    @Component.Builder
    interface Builder {
        fun build(): Injector

        fun networkModule(retrofitModule: RetrofitModule): Builder
    }
}
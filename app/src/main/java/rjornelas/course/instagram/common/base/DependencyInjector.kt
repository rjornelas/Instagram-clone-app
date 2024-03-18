package rjornelas.course.instagram.common.base

import rjornelas.course.instagram.add.data.AddFakeRemoteDataSource
import rjornelas.course.instagram.add.data.AddLocalDataSource
import rjornelas.course.instagram.add.data.AddRepository
import rjornelas.course.instagram.home.data.FeedMemoryCache
import rjornelas.course.instagram.home.data.HomeDataSourceFactory
import rjornelas.course.instagram.home.data.HomeRepository
import rjornelas.course.instagram.login.data.FakeDataSource
import rjornelas.course.instagram.login.data.LoginRepository
import rjornelas.course.instagram.profile.data.PostsListMemoryCache
import rjornelas.course.instagram.profile.data.ProfileDataSourceFactory
import rjornelas.course.instagram.profile.data.ProfileMemoryCache
import rjornelas.course.instagram.profile.data.ProfileRepository
import rjornelas.course.instagram.register.data.FakeRegisterSource
import rjornelas.course.instagram.register.data.RegisterRepository
import rjornelas.course.instagram.splash.data.FakeLocalSplashDataSource
import rjornelas.course.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalSplashDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(
            ProfileDataSourceFactory(ProfileMemoryCache, PostsListMemoryCache)
        )
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(
            HomeDataSourceFactory(FeedMemoryCache)
        )
    }

    fun addRepository(): AddRepository {
        return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
    }
}
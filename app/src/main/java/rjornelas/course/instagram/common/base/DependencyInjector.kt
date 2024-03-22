package rjornelas.course.instagram.common.base

import android.content.Context
import rjornelas.course.instagram.add.data.AddLocalDataSource
import rjornelas.course.instagram.add.data.AddRepository
import rjornelas.course.instagram.add.data.FireAddDataSource
import rjornelas.course.instagram.home.data.FeedMemoryCache
import rjornelas.course.instagram.home.data.HomeDataSourceFactory
import rjornelas.course.instagram.home.data.HomeRepository
import rjornelas.course.instagram.login.data.FireLoginDataSource
import rjornelas.course.instagram.login.data.LoginRepository
import rjornelas.course.instagram.post.data.PostLocalDataSource
import rjornelas.course.instagram.post.data.PostRepository
import rjornelas.course.instagram.profile.data.PostsListMemoryCache
import rjornelas.course.instagram.profile.data.ProfileDataSourceFactory
import rjornelas.course.instagram.profile.data.ProfileMemoryCache
import rjornelas.course.instagram.profile.data.ProfileRepository
import rjornelas.course.instagram.register.data.FireRegisterSource
import rjornelas.course.instagram.register.data.RegisterRepository
import rjornelas.course.instagram.search.data.FireSearchDataSource
import rjornelas.course.instagram.search.data.SearchRepository
import rjornelas.course.instagram.splash.data.FireSplashDataSource
import rjornelas.course.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FireLoginDataSource())
    }

    fun splashRepository(): SplashRepository {
        return SplashRepository(FireSplashDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FireRegisterSource())
    }

    fun searchRepository(): SearchRepository {
        return SearchRepository(FireSearchDataSource())
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
        return AddRepository(FireAddDataSource(), AddLocalDataSource())
    }

    fun postRepository(context: Context): PostRepository {
        return PostRepository(PostLocalDataSource(context))
    }
}
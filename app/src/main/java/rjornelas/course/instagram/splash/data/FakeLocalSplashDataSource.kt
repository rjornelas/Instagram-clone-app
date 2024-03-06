package rjornelas.course.instagram.splash.data

import rjornelas.course.instagram.common.model.Database

class FakeLocalSplashDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if(Database.sessionAuth !=null){
            callback.onSuccess()
        }else{
            callback.onFailure()
        }
    }

}
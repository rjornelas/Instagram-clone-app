package rjornelas.course.instagram.add

import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface Add {

    interface Presenter : BasePresenter

    interface View : BaseView<Presenter>
}
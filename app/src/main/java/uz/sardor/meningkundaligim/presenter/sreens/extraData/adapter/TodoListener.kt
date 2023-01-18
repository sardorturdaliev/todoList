package uz.sardor.meningkundaligim.presenter.sreens.extraData.adapter

import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity

interface TodoListener {
    fun checkBoxCliked(extraDataEnity: ExtraDataEnity,isCheked: Boolean)
}
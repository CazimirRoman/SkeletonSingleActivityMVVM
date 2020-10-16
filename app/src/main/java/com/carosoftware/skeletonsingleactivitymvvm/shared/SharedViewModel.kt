package com.carosoftware.skeletonsingleactivitymvvm.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carosoftware.skeletonsingleactivitymvvm.eventbus.EventBusShowHintsEvent
import com.carosoftware.skeletonsingleactivitymvvm.model.HintType
import com.carosoftware.skeletonsingleactivitymvvm.model.RatingDialog
import com.carosoftware.skeletonsingleactivitymvvm.util.SharedPreferencesUtil
import com.carosoftware.skeletonsingleactivitymvvm.util.SharedPreferencesUtil.RATING_DIALOG
import com.carosoftware.skeletonsingleactivitymvvm.util.SharedPreferencesUtil.loadFromSharedPreferences
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class SharedViewModel : ViewModel() {

    var splashScreenShown: Boolean = false

    // TODO: 27.06.2020 show hints on certain screens?
//    var showHintOnExpanded: ShowHintHistoryOnExpanded =
//        loadFromSharedPreferences<ShowHintHistoryOnExpanded>(
//            SHOW_HISTORY_ON_EXP_KEY
//        ) ?: ShowHintHistoryOnExpanded(true)
//    var showHintDeleteHistory: ShowHintDeleteHistory =
//        loadFromSharedPreferences<ShowHintDeleteHistory>(
//            HISTORY_DELETE_HINTS_KEY
//        ) ?: ShowHintDeleteHistory(true)

    private var _adsBought: MutableLiveData<Boolean> = MutableLiveData(false)
    val adsBought: LiveData<Boolean> = _adsBought
    private var _proBought: MutableLiveData<Boolean> = MutableLiveData(false)
    val proBought: LiveData<Boolean> = _proBought

    private val _showRatingPopup: MutableLiveData<Boolean> = MutableLiveData(false)
    val showRatingPopup: LiveData<Boolean> = _showRatingPopup

    var isRatingDialogShowing: Boolean = false

    init {
        EventBus.getDefault().register(this)
        shouldShowRatingDialog()
    }

    fun updateBoughtAds() {
        _adsBought.value = true
    }

    fun updateBoughtPro() {
        _proBought.value = true
    }

    private fun updateShouldShowHints(hintType: HintType, show: Boolean) {

        when (hintType) {
            HintType.DELETE_HISTORY -> {
                // update shared preferences and shared view model variable
//                SharedPreferencesUtil.saveToSharedPreferences(
//                    ShowHintDeleteHistory(show),
//                    HISTORY_DELETE_HINTS_KEY
//                )
//                showHintDeleteHistory = ShowHintDeleteHistory(show)
            }

            HintType.SHOW_HISTORY_ON_EXP -> {
                // update shared preferences and shared view model variable
//                SharedPreferencesUtil.saveToSharedPreferences(
//                    ShowHintHistoryOnExpanded(show),
//                    SHOW_HISTORY_ON_EXP_KEY
//                )
//                showHintOnExpanded = ShowHintHistoryOnExpanded(show)
            }
        }
    }

    private fun shouldShowRatingDialog() {
        when (val storedRatingInfo = loadFromSharedPreferences<RatingDialog>(RATING_DIALOG)) {
            null -> SharedPreferencesUtil.saveToSharedPreferences(
                RatingDialog(timesOpened = 1),
                RATING_DIALOG
            )
            else -> {
                when {
                    storedRatingInfo.show -> {
                        _showRatingPopup.value = true
                    }

                    storedRatingInfo.never -> {
                        Timber.d("User already submitted review")
                    }

                    else -> {
                        SharedPreferencesUtil.saveToSharedPreferences(
                            RatingDialog(
                                later = storedRatingInfo.later,
                                timesOpened = storedRatingInfo.timesOpened + 1
                            ), RATING_DIALOG
                        )
                        Timber.d(
                            "saveToSharedPreferences: ${loadFromSharedPreferences<RatingDialog>(
                                RATING_DIALOG
                            )}"
                        )
                    }
                }
            }
        }
    }

    fun updateShowRatingDialog() {
        _showRatingPopup.value = false
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun showHintsOrNot(showHintsEvent: EventBusShowHintsEvent) {
        updateShouldShowHints(showHintsEvent.hintType, showHintsEvent.show)
    }
}
package app.real.estate

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController

/**
 * @author : Lee Jae Young
 * @since : 2024-04-01 오전 12:28
 **/
object UtilClass {
    fun fullScreen(activity: Activity) {
        // API 레벨 30부터 사용 가능합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.setDecorFitsSystemWindows(false)
            val controller = activity.window.insetsController

            // 상태 표시줄 및 시스템 바를 숨기는 옵션
            controller?.let {
                it.hide(
                    WindowInsets.Type.navigationBars())
                it.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // API 레벨 30 미만의 경우 이전 방식을 사용
            activity.window.decorView.apply {
                systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                                View.SYSTEM_UI_FLAG_IMMERSIVE)
            }
        }

    }
}
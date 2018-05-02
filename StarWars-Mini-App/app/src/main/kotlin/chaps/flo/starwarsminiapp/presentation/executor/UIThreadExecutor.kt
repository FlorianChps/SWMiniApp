package chaps.flo.starwarsminiapp.presentation.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class UIThreadExecutor : Executor {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable?) {
        handler.post(runnable)
    }
}
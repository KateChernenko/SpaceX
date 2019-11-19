package com.kchernenko.spacex.extention

import com.kchernenko.spacex.R
import com.kchernenko.spacex.ui.fragment.BaseFragment

fun BaseFragment.dialogErrorSomethingWrong(message:String) {
    context?.let {
        androidx.appcompat.app.AlertDialog.Builder(android.view.ContextThemeWrapper(context, R.style.AlertDialogStyle))
            .setMessage(message)
            .show()
    }
}

package com.surkhojb.groceryapp.feature.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.surkhojb.groceryapp.R
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*

interface OnButtonClickListener{
    fun onClick(customDialog: CustomDialog)
}


class CustomDialog private constructor(builder: Builder): BottomSheetDialogFragment(){

    enum class Type{
        ERROR,
        INFO,
        SUCCESS
    }

    private val background: Int = builder.alertBackground ?: R.color.colorError
    private val icon: Int = builder.alertIcon ?: R.drawable.ic_check
    private val title: String = builder.alertTitle ?: getString(R.string.error_title)
    private val message: String = builder.alertMessage ?: getString(R.string.error_message)
    private val buttonText: String = builder.primaryButtonText ?: getString(R.string.action_done_title)
    private val listener: OnButtonClickListener? =  builder.alertListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_alert_dialog,container,false)
        view.alert_background.setBackgroundColor(background)
        view.alert_icon.setImageResource(icon)
        view.alert_title.text = title
        view.alert_message.text = message
        view.alert_button.text = buttonText
        view.alert_button.setOnClickListener {
            listener?.onClick(this)
        }

        return view
    }

    class Builder {
        var alertBackground: Int? = null
        var alertIcon: Int? = null
        var alertTitle: String? = ""
        var alertMessage: String? = ""
        var primaryButtonText: String? = ""
        var alertListener: OnButtonClickListener? = null

        fun background(color: Int): Builder {
            alertBackground = color
            return this
        }

        fun icon(drawable: Int): Builder {
            alertIcon = drawable
            return this
        }

        fun title(title: String): Builder {
            alertTitle = title
            return this
        }

        fun message(message: String): Builder {
            alertMessage = message
            return this
        }

        fun buttonText(text: String): Builder {
            primaryButtonText = text
            return this
        }

        fun setListener(listener: OnButtonClickListener): Builder {
            alertListener = listener
            return this
        }

        fun build(): CustomDialog {
            return CustomDialog(this)
        }
    }
}
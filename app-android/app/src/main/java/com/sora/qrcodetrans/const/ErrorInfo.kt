package com.sora.qrcodetrans.const

class ErrorInfo {
    object Code {

    }

    object Message {
        /**
         * Lỗi timeout
         */
        const val Timeout = "Lỗi kết nối tới server"

        /**
         * Lỗi chung
         */
        const val Error = "Có lỗi xảy ra"
    }
}
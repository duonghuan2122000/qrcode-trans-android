package com.sora.qrcodetrans.const

class TransactionQRCodeErrorInfo {
    object Code {
        /**
         * Thành công
         */
        const val Success = "00"

        /**
         * Đơn hàng đã được thanh taons
         */
        const val Paid = "03"

        /**
         * QR hết hạn
         */
        const val Expired = "09"
    }

    object Message {
        /**
         * Thành công
         */
        const val Success = "Thanh toán QR thành công"

        /**
         * Đơn hàng đã được thanh taons
         */
        const val Paid = "Đơn hàng đã được thanh toán"

        /**
         * QR hết hạn
         */
        const val Expired = "QR hết hạn thanh toán"

        const val Error = "Có lỗi xảy ra khi thanh toán QR"
    }
}
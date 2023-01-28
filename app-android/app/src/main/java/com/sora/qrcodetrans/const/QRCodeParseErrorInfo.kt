package com.sora.qrcodetrans.const

class QRCodeParseErrorInfo {
    /**
     * Mã lỗi
     */
    object Code {
        /**
         * QR không hợp lệ
         */
        const val QRCodeInValid = "E1000"

        /**
         * QR đã thanh toán
         */
        const val QRCodePaid = "E1001"

        /**
         * Lỗi chung
         */
        const val Error = "E3000"
    }

    object Message {
        /**
         * QR không hợp lệ
         */
        const val QRCodeInValid = "QR không hợp lệ"

        /**
         * QR đã thanh toán
         */
        const val QRCodePaid = "QR đã được thanh toán"

        /**
         * Lỗi chung
         */
        const val Error = "QR không hợp lệ"
    }
}
﻿// <auto-generated> This file has been auto generated by EF Core Power Tools. </auto-generated>
#nullable disable
using System;
using System.Collections.Generic;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Bảng lưu thông tin giao dịch
    /// </summary>
    public partial class Transaction
    {
        /// <summary>
        /// Khóa chính
        /// </summary>
        public Guid Id { get; set; }
        /// <summary>
        /// Mã đơn hàng
        /// </summary>
        public string TxnId { get; set; }
        /// <summary>
        /// Mã điểm thu
        /// </summary>
        public string TerminalId { get; set; }
        /// <summary>
        /// Số tiền
        /// </summary>
        public decimal Amount { get; set; }
        /// <summary>
        /// Mã ngân hàng
        /// </summary>
        public string MasterMerCode { get; set; }
        /// <summary>
        /// Mã merchant ngân hàng quy định
        /// </summary>
        public string MerchantCode { get; set; }
        /// <summary>
        /// Thời gian tạo giao dịch
        /// </summary>
        public DateTime CreatedDate { get; set; }
    }
}
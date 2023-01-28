using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    public partial class TransactionMerReqDto
    {

        public TransactionMerReqDto()
        {
            BankCode = "BankTest";
        }

        public string Code { get; set; }
        public string Message { get; set; }
        public string MsgType { get; set; }
        public string TxnId { get; set; }
        public string QrTrace { get; set; }
        public string BankCode { get; set; }
        public string Mobile { get; set; }
        public string AccountNo { get; set; }
        public string Amount { get; set; }
        public string PayDate { get; set; }
        public string MasterMerCode { get; set; }
        public string MerchantCode { get; set; }
        public string TerminalId { get; set; }
        public string Name { get; set; }
        public string Phone { get; set; }
        public string ProvinceId { get; set; }
        public string DistrictId { get; set; }
        public string Address { get; set; }
        public string Email { get; set; }
    }
}

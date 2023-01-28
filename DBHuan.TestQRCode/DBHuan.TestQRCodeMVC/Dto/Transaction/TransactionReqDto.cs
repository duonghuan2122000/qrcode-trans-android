namespace DBHuan.TestQRCodeMVC
{
    public class TransactionReqDto
    {
        public string MerchantCode { get; set; }

        public string TerminalId { get; set; }

        public string TxnId { get; set; }

        public string Amount { get; set; }
    }
}
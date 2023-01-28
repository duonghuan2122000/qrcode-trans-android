namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Dto của api lưu thông tin config qrcode
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigQRCodeDto
    {
        public string ClientId { get; set; }

        public string ClientSecret { get; set; }

        public string UrlParseQRCode { get; set; }

        public string UrlTransactionQRCode { get; set; }
    }
}
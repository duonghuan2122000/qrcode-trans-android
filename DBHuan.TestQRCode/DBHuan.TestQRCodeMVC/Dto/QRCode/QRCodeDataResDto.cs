using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    ///Thông tin qrcode
    /// </summary>
    /// CreatedBy: dbhuan 22/01//2022
    public class QRCodeDataResDto
    {
        public string MerchantCode { get; set; }

        public string MerchantName { get; set; }

        public string TerminalId { get; set; }

        public string TxnId { get; set; }

        public string Amount { get; set; }

        public string Purpose { get; set; }
    }
}

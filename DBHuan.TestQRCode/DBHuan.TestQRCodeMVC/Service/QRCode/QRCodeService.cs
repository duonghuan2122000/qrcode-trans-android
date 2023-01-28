using System;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Service của QRCode
    /// </summary>
    /// CreatedBy: dbhuan 22/01/2022
    public class QRCodeService : IQRCodeService
    {
        private readonly ITransactionRepository _transactionRepo;

        public QRCodeService(ITransactionRepository transactionRepo)
        {
            _transactionRepo = transactionRepo;
        }

        public async Task<ResponseDto<QRCodeDataResDto>> ParseQRCode(string data)
        {
            var qrcode = new QRCodeDataResDto
            {
                MerchantCode = "DBHuanTest",
                MerchantName = "DBHuan QRCode Test",
                TerminalId = "DBHuan001",
                TxnId = data.Length > 15 ? data.Substring(0, 14) : data,
                Amount = RandomNumber(2000, 20000).ToString(),
                Purpose = "Test QRCode"
            };

            var res = new ResponseDto<QRCodeDataResDto>
            {
                IsSuccess = true
            };

            if (qrcode.TxnId.Equals("error"))
            {
                res.IsSuccess = false;
                res.ErrorCode = "E1000";
                return res;
            }

            var isExistsTrans = await _transactionRepo.IsExists(qrcode.TxnId, qrcode.TerminalId);

            if (isExistsTrans)
            {
                res.IsSuccess = false;
                res.ErrorCode = "E1001";
                return res;
            }

            res.Data = qrcode;
            return res;
        }

        private int RandomNumber(int min, int max)
        {
            return new Random().Next(min, max);
        }
    }
}
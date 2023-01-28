using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    public class TransactionService : ITransactionService
    {
        private readonly ITransactionRepository _transactionRepo;

        public TransactionService(ITransactionRepository transactionRepo)
        {
            _transactionRepo = transactionRepo;
        }

        public async Task<TransactionMerResDto> CreateTransactionMerQRCode(TransactionMerReqDto input)
        {
            var res = new TransactionMerResDto
            {
                Code = "00",
                Message = "Đặt hàng thành công"
            };

            return res;
        }

        public async Task<ResponseDto<TransactionResDto>> CreateTransactionQRCode(TransactionReqDto input)
        {
            var res = new ResponseDto<TransactionResDto>
            {
                IsSuccess = false
            };

            var isExistsTrans = await _transactionRepo.IsExists(input.TxnId, input.TerminalId);

            if (isExistsTrans)
            {
                res.IsSuccess = false;
                res.ErrorCode = "03";
                return res;
            }

            var transaction = new Transaction
            {
                TxnId = input.TxnId,
                TerminalId = input.TerminalId,
                Amount = decimal.Parse(input.Amount),
                MerchantCode = input.MerchantCode
            };

            await _transactionRepo.InsertAsync(transaction);

            res.IsSuccess = true;
            res.ErrorCode = "00";

            return res;
        }
    }
}

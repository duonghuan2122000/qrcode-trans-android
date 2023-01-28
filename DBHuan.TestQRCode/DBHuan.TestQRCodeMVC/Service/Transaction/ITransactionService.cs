using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    public interface ITransactionService
    {
        Task<ResponseDto<TransactionResDto>> CreateTransactionQRCode(TransactionReqDto input);

        Task<TransactionMerResDto> CreateTransactionMerQRCode(TransactionMerReqDto input);
    }
}
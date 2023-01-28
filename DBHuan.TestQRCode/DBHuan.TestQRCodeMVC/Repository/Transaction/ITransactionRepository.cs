using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    public interface ITransactionRepository
    {
        Task<bool> IsExists(string txnId, string terminalId);

        Task InsertAsync(Transaction transaction);
    }
}

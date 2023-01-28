using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    public class TransactionRepository : ITransactionRepository
    {
        private readonly TestQRCodeDbContext _dbContext;

        public TransactionRepository(TestQRCodeDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task InsertAsync(Transaction transaction)
        {
            await _dbContext.Transaction.AddAsync(transaction);
            await _dbContext.SaveChangesAsync();
        }

        public async Task<bool> IsExists(string txnId, string terminalId)
        {
            var hasTrans = await _dbContext.Transaction.AsNoTracking()
                .Where(t => t.TxnId == txnId && t.TerminalId == terminalId)
                .CountAsync();

            return hasTrans >= 1;
        }
    }
}
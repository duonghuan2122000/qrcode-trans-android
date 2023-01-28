using System;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Bảng transaction
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public partial class Transaction
    {
        public Transaction()
        {
            Id = Guid.NewGuid();
            CreatedDate = DateTime.Now;
        }
    }
}
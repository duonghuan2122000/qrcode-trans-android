using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Interface repo config
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public interface IConfigRepository
    {
        /// <summary>
        /// Lấy tất cả thông số cấu hình
        /// </summary>
        Task<List<Config>> GetListAsync();
    }
}

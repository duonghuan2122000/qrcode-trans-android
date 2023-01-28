using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Config repo
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigRepository
    {
        private TestQRCodeDbContext _dbContext;

        /// <summary>
        /// Hàm khởi tạo
        /// </summary>
        /// CreatedBy: dbhuan 21/01//2022
        public ConfigRepository(TestQRCodeDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        /// <summary>
        /// Lấy tất cả thông số cấu hình
        /// </summary>
        public async Task<List<Config>> GetListAsync()
        {
            var configs = await _dbContext.Config.AsNoTracking()
                .ToListAsync();

            return configs;
        }
    }
}

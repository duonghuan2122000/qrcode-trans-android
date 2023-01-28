using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Config repo
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigRepository: IConfigRepository
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

        /// <summary>
        /// Cập nhật config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        public async Task UpdateAsync(List<Config> configs)
        {
            foreach(var config in configs)
            {
                var c = await _dbContext.Config.FirstOrDefaultAsync(cf => cf.Key == config.Key);
                c.Value = config.Value;
                _dbContext.Update(c);
                await _dbContext.SaveChangesAsync();
            }
        }
    }
}

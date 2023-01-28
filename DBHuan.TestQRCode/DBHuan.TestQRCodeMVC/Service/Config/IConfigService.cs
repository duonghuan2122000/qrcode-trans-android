using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Service của config
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public interface IConfigService
    {
        /// <summary>
        /// Lấy danh sách config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        Task<ResponseDto<ConfigQRCodeDto>> GetListAsync();

        /// <summary>
        /// Cập nhật config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        Task UpdateAsync(ConfigQRCodeDto config);
    }
}

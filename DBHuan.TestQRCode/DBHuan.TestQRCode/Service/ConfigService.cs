using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Service của config
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigService
    {
        /// <summary>
        /// Lấy danh sách config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        public async Task<ResponseDto<Dictionary<string, string>>> GetListAsync()
        {
            var resDto = new ResponseDto<Dictionary<string, string>>();

            

            return resDto;
        }
    }
}

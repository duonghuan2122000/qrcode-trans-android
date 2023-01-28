using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Interface service của QRCode
    /// </summary>
    /// CreatedBy: dbhuan 22/01/2022
    public interface IQRCodeService
    {
        Task<ResponseDto<QRCodeDataResDto>> ParseQRCode(string data);
    }
}

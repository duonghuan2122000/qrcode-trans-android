using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Confit Dto
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigDto
    {
        /// <summary>
        /// Khóa chính
        /// </summary>
        public Guid Id { get; set; }
        public string Key { get; set; }
        public string Value { get; set; }
    }
}

using System.Collections.Generic;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Service của config
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ConfigService: IConfigService
    {
        private readonly IConfigRepository _configRepo;

        public ConfigService(IConfigRepository configRepo)
        {
            _configRepo = configRepo;
        }

        /// <summary>
        /// Lấy danh sách config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        public async Task<ResponseDto<ConfigQRCodeDto>> GetListAsync()
        {
            var resDto = new ResponseDto<ConfigQRCodeDto>();

            var configs = await _configRepo.GetListAsync();

            resDto.IsSuccess = true;
            resDto.Data = new ConfigQRCodeDto();

            foreach (var config in configs)
            {
                switch (config.Key)
                {
                    case "ClientId":
                        resDto.Data.ClientId = config.Value;
                        break;

                    case "ClientSecret":
                        resDto.Data.ClientSecret = config.Value;
                        break;

                    case "UrlParseQRCode":
                        resDto.Data.UrlParseQRCode = config.Value;
                        break;

                    case "UrlTransactionQRCode":
                        resDto.Data.UrlTransactionQRCode = config.Value;
                        break;
                }
            }

            return resDto;
        }

        /// <summary>
        /// Cập nhật config
        /// </summary>
        /// CreatedBy: dbhuan 21/01/2022
        public async Task UpdateAsync(ConfigQRCodeDto config)
        {
            var cs = new List<Config>
            {
                new Config
                {
                    Key = "ClientId",
                    Value = config.ClientId
                },
                new Config
                {
                    Key = "ClientSecret",
                    Value = config.ClientSecret
                },
                new Config
                {
                    Key = "UrlParseQRCode",
                    Value = config.UrlParseQRCode
                },
                new Config
                {
                    Key = "UrlTransactionQRCode",
                    Value = config.UrlTransactionQRCode
                }
            };
            await _configRepo.UpdateAsync(cs);
        }
    }
}
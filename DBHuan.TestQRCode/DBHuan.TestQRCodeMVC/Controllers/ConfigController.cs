using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC.Controllers
{
    /// <summary>
    /// Controller cho config
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    [Route("config")]
    public class ConfigController : Controller
    {
        private readonly IConfigService _configService;

        public ConfigController(IConfigService configService)
        {
            _configService = configService;
        }

        [HttpGet("")]
        public async Task<IActionResult> ConfigQRCodeView()
        {
            var configs = await _configService.GetListAsync();
            ViewBag.Config = configs.Data;
            return View("ConfigQRCode");
        }

        [HttpPost("")]
        public async Task<IActionResult> SaveConfigQRCode(ConfigQRCodeDto config)
        {
            await _configService.UpdateAsync(config);
            return Redirect("/config");
        }

        [HttpGet("json")]
        public async Task<ResponseDto<ConfigQRCodeDto>> ConfigQRCode()
        {
            var configs = await _configService.GetListAsync();
            return configs;
        }
    }
}
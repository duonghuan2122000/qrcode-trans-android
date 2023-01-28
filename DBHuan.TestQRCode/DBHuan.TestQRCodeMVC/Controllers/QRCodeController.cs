using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC.Controllers
{
    [Route("qrcode")]
    public class QRCodeController : Controller
    {
        private readonly IQRCodeService _qrcodeService;
        private readonly ITransactionService _transactionService;

        public QRCodeController(IQRCodeService qrcodeService, ITransactionService transactionService)
        {
            _qrcodeService = qrcodeService;
            _transactionService = transactionService;
        }

        [HttpGet]
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost("parse/json")]
        public async Task<IActionResult> ParseQRCode(string data)
        {
            var res = await _qrcodeService.ParseQRCode(data);
            return Ok(res);
        }

        [HttpPost("transaction/json")]
        public async Task<IActionResult> CreateTransaction(TransactionReqDto input)
        {
            var res = await _transactionService.CreateTransactionQRCode(input);
            return Ok(res);
        }

        //[HttpPost("transaction/mer/json")]
        //public async Task<>
    }
}

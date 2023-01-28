using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC.Controllers
{
    [Route("auth")]
    public class AuthController : Controller
    {
        private readonly IConfiguration _configuration;

        public AuthController(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        [HttpPost("token")]
        public IActionResult GetToken([FromForm] GetTokenReq input)
        {
            if(input.Client_id == "dbhuan-client" && input.Client_secret == "abc")
            {
                var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Jwt:Key"]));
                var signIn = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
                var token = new JwtSecurityToken(_configuration["Jwt:Issuer"], _configuration["Jwt:Issuer"], null, expires: DateTime.Now.AddDays(1), signingCredentials: signIn);

                var res = new GetTokenRes
                {
                    Access_token = new JwtSecurityTokenHandler().WriteToken(token)
                };
                return Ok(res);
            }
            return Ok(new
            {
                invalid_client = true
            });
        }
    }

    public class GetTokenRes
    {
        public string Access_token { get; set; }
    }

    public class GetTokenReq
    {
        public string Client_id { get; set; }

        public string Client_secret { get; set; }
    }
}

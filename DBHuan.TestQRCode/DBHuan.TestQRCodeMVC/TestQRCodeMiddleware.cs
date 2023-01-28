using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC
{
    /// <summary>
    /// Middleware dùng chung cho toàn ứng dụng
    /// </summary>
    /// CreatedBy: dhbuan 21/01/2022
    public class TestQRCodeMiddleware
    {
        private readonly RequestDelegate _next;

        public TestQRCodeMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            try
            {
                await _next(context);
            }
            catch (Exception ex)
            {
                await HandleException(context, ex);
            }
        }

        private async Task HandleException(HttpContext context, Exception ex)
        {
            var response = new ResponseDto<string>();

            response.IsSuccess = false;
            response.ErrorCode = "E3000";

            context.Response.StatusCode = 200;
            context.Response.ContentType = "application/json";
            var objRes = JsonConvert.SerializeObject(response);
            await context.Response.WriteAsync(objRes);
        }
    }
}

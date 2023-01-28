using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;

namespace DBHuan.TestQRCodeMVC.Lib
{
    public class CallService
    {
        public async Task<CallServiceResponse<T>> CreateReqAsync<T>(string url, HttpMethod method, string contentType = "application/json", object req = null, Dictionary<string, string> headers = null)
        {
            var res = new CallServiceResponse<T>();

            var httpClient = new HttpClient();

            var request = new HttpRequestMessage
            {
                Method = method,
                RequestUri = new Uri(url),
                Content = new StringContent(JsonConvert.SerializeObject(req))
            };

            request.Headers.Add("Content-Type", contentType);

            if(headers != null)
            {
                foreach(var itemHeader in headers)
                {
                    request.Headers.Add(itemHeader.Key, itemHeader.Value);
                }
            }

            var response = await httpClient.SendAsync(request);

            switch (response.StatusCode)
            {
                case HttpStatusCode.OK:
                    res.StatusCode = HttpStatusCode.OK;
                    res.Result = JsonConvert.DeserializeObject<T>(await response.Content.ReadAsStringAsync());
                    break;
                default:
                    res.StatusCode = response.StatusCode;
                    res.Message = await response.Content.ReadAsStringAsync();
                    break;
            }

            return res;
        }
    }

    public class CallServiceResponse<T>
    {
        public T Result { get; set; }

        public string Message { get; set; }

        public HttpStatusCode StatusCode { get; set; }
    }
}

#pragma checksum "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "b1939a64d7a3abbd28cdb791fa761775c769d3b5"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Config_ConfigQRCode), @"mvc.1.0.view", @"/Views/Config/ConfigQRCode.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\_ViewImports.cshtml"
using DBHuan.TestQRCodeMVC;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\_ViewImports.cshtml"
using DBHuan.TestQRCodeMVC.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"b1939a64d7a3abbd28cdb791fa761775c769d3b5", @"/Views/Config/ConfigQRCode.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"359a5cdc52039f884d343af9e8ad4802957e7a1f", @"/Views/_ViewImports.cshtml")]
    public class Views_Config_ConfigQRCode : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("action", new global::Microsoft.AspNetCore.Html.HtmlString("/config"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("method", "post", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 1 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml"
  
    ViewData["Title"] = "Config QRCode";
    Layout = "_Layout";
    ConfigQRCodeDto config = ViewBag.Config;

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<div class=\"container\">\r\n    <h1 class=\"fs-3\">Cấu hình cho QRCode</h1>\r\n\r\n    ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "b1939a64d7a3abbd28cdb791fa761775c769d3b54303", async() => {
                WriteLiteral("\r\n        <div class=\"form-floating mb-3 mt-4\">\r\n            <input type=\"text\" class=\"form-control\" name=\"ClientId\" id=\"ClientId\" placeholder=\"ClientId\"");
                BeginWriteAttribute("value", " value=\"", 390, "\"", 414, 1);
#nullable restore
#line 12 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml"
WriteAttributeValue("", 398, config.ClientId, 398, 16, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(">\r\n            <label for=\"ClientId\">ClientId</label>\r\n        </div>\r\n        <div class=\"form-floating mb-3\">\r\n            <input type=\"text\" class=\"form-control\" name=\"ClientSecret\" id=\"ClientSecret\" placeholder=\"ClientSecret\"");
                BeginWriteAttribute("value", " value=\"", 644, "\"", 672, 1);
#nullable restore
#line 16 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml"
WriteAttributeValue("", 652, config.ClientSecret, 652, 20, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(">\r\n            <label for=\"ClientSecret\">ClientSecret</label>\r\n        </div>\r\n        <div class=\"form-floating mb-3\">\r\n            <input type=\"text\" class=\"form-control\" name=\"UrlParseQRCode\" id=\"UrlParseQRCode\" placeholder=\"Url parse QRCode\"");
                BeginWriteAttribute("value", " value=\"", 918, "\"", 948, 1);
#nullable restore
#line 20 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml"
WriteAttributeValue("", 926, config.UrlParseQRCode, 926, 22, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(@">
            <label for=""UrlParseQRCode"">Url parse QRCode</label>
        </div>
        <div class=""form-floating mb-3"">
            <input type=""text"" class=""form-control"" name=""UrlTransactionQRCode"" id=""UrlTransactionQRCode"" placeholder=""Url thanh toán QRCode""");
                BeginWriteAttribute("value", " value=\"", 1217, "\"", 1253, 1);
#nullable restore
#line 24 "E:\android\qrcode\DBHuan.TestQRCode\DBHuan.TestQRCodeMVC\Views\Config\ConfigQRCode.cshtml"
WriteAttributeValue("", 1225, config.UrlTransactionQRCode, 1225, 28, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(">\r\n            <label for=\"UrlTransactionQRCode\">Url thanh toán QRCode</label>\r\n        </div>\r\n        <div>\r\n            <button class=\"btn btn-primary\" type=\"submit\">Lưu thay đổi</button>\r\n        </div>\r\n    ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string)__tagHelperAttribute_1.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_1);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n</div>\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591
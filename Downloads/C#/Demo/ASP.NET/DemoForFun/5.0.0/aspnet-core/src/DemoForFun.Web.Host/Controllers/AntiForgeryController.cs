using Microsoft.AspNetCore.Antiforgery;
using DemoForFun.Controllers;

namespace DemoForFun.Web.Host.Controllers
{
    public class AntiForgeryController : DemoForFunControllerBase
    {
        private readonly IAntiforgery _antiforgery;

        public AntiForgeryController(IAntiforgery antiforgery)
        {
            _antiforgery = antiforgery;
        }

        public void GetToken()
        {
            _antiforgery.SetCookieTokenAndHeader(HttpContext);
        }
    }
}

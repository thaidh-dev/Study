using Abp.AspNetCore.Mvc.Controllers;
using Abp.IdentityFramework;
using Microsoft.AspNetCore.Identity;

namespace DemoForFun.Controllers
{
    public abstract class DemoForFunControllerBase: AbpController
    {
        protected DemoForFunControllerBase()
        {
            LocalizationSourceName = DemoForFunConsts.LocalizationSourceName;
        }

        protected void CheckErrors(IdentityResult identityResult)
        {
            identityResult.CheckErrors(LocalizationManager);
        }
    }
}

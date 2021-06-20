using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Abp.Modules;
using Abp.Reflection.Extensions;
using DemoForFun.Configuration;

namespace DemoForFun.Web.Host.Startup
{
    [DependsOn(
       typeof(DemoForFunWebCoreModule))]
    public class DemoForFunWebHostModule: AbpModule
    {
        private readonly IHostingEnvironment _env;
        private readonly IConfigurationRoot _appConfiguration;

        public DemoForFunWebHostModule(IHostingEnvironment env)
        {
            _env = env;
            _appConfiguration = env.GetAppConfiguration();
        }

        public override void Initialize()
        {
            IocManager.RegisterAssemblyByConvention(typeof(DemoForFunWebHostModule).GetAssembly());
        }
    }
}

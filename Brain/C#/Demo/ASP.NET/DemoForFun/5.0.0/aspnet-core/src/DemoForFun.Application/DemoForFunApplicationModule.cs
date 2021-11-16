using Abp.AutoMapper;
using Abp.Modules;
using Abp.Reflection.Extensions;
using DemoForFun.Authorization;

namespace DemoForFun
{
    [DependsOn(
        typeof(DemoForFunCoreModule), 
        typeof(AbpAutoMapperModule))]
    public class DemoForFunApplicationModule : AbpModule
    {
        public override void PreInitialize()
        {
            Configuration.Authorization.Providers.Add<DemoForFunAuthorizationProvider>();
        }

        public override void Initialize()
        {
            var thisAssembly = typeof(DemoForFunApplicationModule).GetAssembly();

            IocManager.RegisterAssemblyByConvention(thisAssembly);

            Configuration.Modules.AbpAutoMapper().Configurators.Add(
                // Scan the assembly for classes which inherit from AutoMapper.Profile
                cfg => cfg.AddMaps(thisAssembly)
            );
        }
    }
}

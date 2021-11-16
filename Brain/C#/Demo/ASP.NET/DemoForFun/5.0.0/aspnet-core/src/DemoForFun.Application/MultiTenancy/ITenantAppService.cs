using Abp.Application.Services;
using Abp.Application.Services.Dto;
using DemoForFun.MultiTenancy.Dto;

namespace DemoForFun.MultiTenancy
{
    public interface ITenantAppService : IAsyncCrudAppService<TenantDto, int, PagedTenantResultRequestDto, CreateTenantDto, TenantDto>
    {
    }
}


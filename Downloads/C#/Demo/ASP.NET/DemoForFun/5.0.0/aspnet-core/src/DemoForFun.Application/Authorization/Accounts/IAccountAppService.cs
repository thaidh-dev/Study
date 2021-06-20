using System.Threading.Tasks;
using Abp.Application.Services;
using DemoForFun.Authorization.Accounts.Dto;

namespace DemoForFun.Authorization.Accounts
{
    public interface IAccountAppService : IApplicationService
    {
        Task<IsTenantAvailableOutput> IsTenantAvailable(IsTenantAvailableInput input);

        Task<RegisterOutput> Register(RegisterInput input);
    }
}

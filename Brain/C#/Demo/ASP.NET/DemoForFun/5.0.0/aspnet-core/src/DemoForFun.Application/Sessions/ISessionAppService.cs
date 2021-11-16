using System.Threading.Tasks;
using Abp.Application.Services;
using DemoForFun.Sessions.Dto;

namespace DemoForFun.Sessions
{
    public interface ISessionAppService : IApplicationService
    {
        Task<GetCurrentLoginInformationsOutput> GetCurrentLoginInformations();
    }
}

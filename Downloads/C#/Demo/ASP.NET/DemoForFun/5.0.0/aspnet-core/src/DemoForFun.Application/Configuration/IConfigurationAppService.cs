using System.Threading.Tasks;
using DemoForFun.Configuration.Dto;

namespace DemoForFun.Configuration
{
    public interface IConfigurationAppService
    {
        Task ChangeUiTheme(ChangeUiThemeInput input);
    }
}

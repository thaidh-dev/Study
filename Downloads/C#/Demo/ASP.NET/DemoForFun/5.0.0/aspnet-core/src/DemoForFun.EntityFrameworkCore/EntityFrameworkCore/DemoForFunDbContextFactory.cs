using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;
using DemoForFun.Configuration;
using DemoForFun.Web;

namespace DemoForFun.EntityFrameworkCore
{
    /* This class is needed to run "dotnet ef ..." commands from command line on development. Not used anywhere else */
    public class DemoForFunDbContextFactory : IDesignTimeDbContextFactory<DemoForFunDbContext>
    {
        public DemoForFunDbContext CreateDbContext(string[] args)
        {
            var builder = new DbContextOptionsBuilder<DemoForFunDbContext>();
            var configuration = AppConfigurations.Get(WebContentDirectoryFinder.CalculateContentRootFolder());

            DemoForFunDbContextConfigurer.Configure(builder, configuration.GetConnectionString(DemoForFunConsts.ConnectionStringName));

            return new DemoForFunDbContext(builder.Options);
        }
    }
}

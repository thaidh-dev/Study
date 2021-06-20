using Microsoft.EntityFrameworkCore;
using Abp.Zero.EntityFrameworkCore;
using DemoForFun.Authorization.Roles;
using DemoForFun.Authorization.Users;
using DemoForFun.MultiTenancy;

namespace DemoForFun.EntityFrameworkCore
{
    public class DemoForFunDbContext : AbpZeroDbContext<Tenant, Role, User, DemoForFunDbContext>
    {
        /* Define a DbSet for each entity of the application */
        
        public DemoForFunDbContext(DbContextOptions<DemoForFunDbContext> options)
            : base(options)
        {
        }
    }
}

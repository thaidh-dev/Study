using System.Data.Common;
using Microsoft.EntityFrameworkCore;

namespace DemoForFun.EntityFrameworkCore
{
    public static class DemoForFunDbContextConfigurer
    {
        public static void Configure(DbContextOptionsBuilder<DemoForFunDbContext> builder, string connectionString)
        {
            builder.UseSqlServer(connectionString);
        }

        public static void Configure(DbContextOptionsBuilder<DemoForFunDbContext> builder, DbConnection connection)
        {
            builder.UseSqlServer(connection);
        }
    }
}

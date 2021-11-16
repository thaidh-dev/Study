using Abp.Authorization;
using DemoForFun.Authorization.Roles;
using DemoForFun.Authorization.Users;

namespace DemoForFun.Authorization
{
    public class PermissionChecker : PermissionChecker<Role, User>
    {
        public PermissionChecker(UserManager userManager)
            : base(userManager)
        {
        }
    }
}

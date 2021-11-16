using Microsoft.EntityFrameworkCore;
using Demo.Models;

namespace Demo.Data {
    public class DataContext : DbContext {
        // gọi đến hàm tạo của lớp cha
        public DataContext(DbContextOptions<DataContext> options) : base (options) {
        }

        public DbSet<NhanVien> nhan_vien {get; set;}

        public DbSet<PhongBan> phong_ban {get; set;}

        public DbSet<User> user {get; set;}
    }
}

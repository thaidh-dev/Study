using Microsoft.AspNetCore.Mvc;
using Demo.Data;
using System.Linq;
using System.Collections.Generic;
using Demo.Models;

namespace Demo.Controllers
{
    [Route("[controller]")] // @RequestMapping nằm trên đầu class
    // bắt buộc phải đặt tên lớp có đuôi là 'Controller'
    public class PhongBanController : ControllerBase
    {
        private readonly DataContext _context;

        public PhongBanController(DataContext context) {
            this._context = context;
        }

        [HttpGet("{xc}")]
        public string xinChao(string xc) {
            return xc + " alo";
        }

        [HttpGet]
        public OkObjectResult getAllPhongBan() {
            return Ok(_context.phong_ban.ToList());
        }
    }
}
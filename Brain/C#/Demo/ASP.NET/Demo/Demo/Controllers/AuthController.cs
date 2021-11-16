using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using Demo.Data;
using Demo.Dtos;
using Demo.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
// làm đến 33
namespace Demo.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly IAuthRepository _repo;
        private readonly IConfiguration _config;

        public AuthController(IAuthRepository repo, IConfiguration config)
        {
            _repo = repo;
            _config = config;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(UserForRegisterDto uDto) 
        {
            uDto.username = uDto.username.ToLower();

            if (await _repo.UserExists(uDto.username))
                return BadRequest("User đã tồn tại");

            var userToCreate = new User 
            {
                Username = uDto.username
            };

            var createUser = await _repo.Register(userToCreate, uDto.password);

            return StatusCode(201);
        }

        // [HttpPost("login")]
        // public async Task<ActionResult> Login(UserForLoginDto userForLoginDto) 
        // {
        //     var userFromRepo = await _repo.Login(userForLoginDto.Username, userForLoginDto.Password);

        //     if (userFromRepo == null)
        //         return Unauthorized();

        //     var claims = new[]
        //     {
        //         new Claim(ClaimTypes.NameIdentifier, userFromRepo.id.ToString()),
        //         new Claim(ClaimTypes.Name, userFromRepo.Username)
        //     };

        //     var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config.GetSection("Application:Token").Value));

        //     var creds = new Sign
        // }
    }
}
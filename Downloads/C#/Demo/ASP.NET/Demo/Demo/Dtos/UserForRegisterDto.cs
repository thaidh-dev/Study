using System.ComponentModel.DataAnnotations;

namespace Demo.Dtos
{
    public class UserForRegisterDto
    {
        [Required]
        public string username {get; set;}

        [Required]
        [StringLength(8, MinimumLength = 4, ErrorMessage = "Nhập pass từ 4 -> 8 ký tự")]
        public string password {get; set;}
    }
}
using System.ComponentModel.DataAnnotations;

namespace DemoForFun.Users.Dto
{
    public class ChangeUserLanguageDto
    {
        [Required]
        public string LanguageName { get; set; }
    }
}
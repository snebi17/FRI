using Microsoft.AspNetCore.Mvc;

using SampleAPI.Services;
using SampleAPI.Models;
using AutoMapper;

namespace SampleAPI.Controllers
{
    public class UserController : ControllerBase
    {
        private IUserService userService;
        private IMapper mapper;

        UserController(IUserService userService, IMapper mapper)
        {
            this.userService = userService;
            this.mapper = mapper;
        }

        [HttpGet]
        public IActionResult GetUsers()
        {
            var users = userService.GetUsers();
            return Ok(users);
        }

        [HttpGet("{id}")]
        public IActionResult GetUser(int id)
        {
            var user = userService.GetUser(id);
            return Ok(user);
        }

        [HttpPost]
        public IActionResult Authenticate(AuthRequest model)
        {
            var authRes = userService.Authenticate(model);
            return Ok(authRes);
        }
    }
}

using SampleAPI.Entities;
using SampleAPI.Models;

namespace SampleAPI.Services
{
    public interface IUserService
    {
        AuthResponse Authenticate(AuthRequest model);
        void Register(User model);
        User GetUser(int id);
        List<User> GetUsers();
    }
    public class UserService : IUserService
    {
        public AuthResponse Authenticate(AuthRequest model)
        {
            return new AuthResponse();
        }

        public void Register(User model)
        {
            return;
        }

        public User GetUser(int id)
        {
            return new User();
        }

        public List<User> GetUsers()
        {
            return new List<User>();
        }
    }
}

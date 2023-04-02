namespace web.Models {
    public class User {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PasswordHash { get; set; }
        public DateTime DateCreated { get; set; }
        public ICollection<Post>? Posts { get; set; }
        public ICollection<User>? Friends { get; set; }

        public User() 
        {
            DateCreated = DateTime.Now;
        }
    }
}
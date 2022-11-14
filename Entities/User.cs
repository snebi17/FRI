namespace SampleAPI.Entities
{
    public class User
    {
        public int Id { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string PasswordHash { get; set; }
        public List<Trip> Trips { get; set; }
        public List<Review> Reviews { get; set; }
    }
}

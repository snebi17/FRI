﻿namespace SampleAPI.Models
{
    public class AuthResponse
    {
        public int Id { get; set; }
        public string Username { get; set; }
        public string Token { get; set; }
        public string ExpirationDate { get; set; }
    }
}

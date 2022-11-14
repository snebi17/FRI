using System.ComponentModel.DataAnnotations;

namespace SampleAPI.Entities
{
    public class Trip
    {
        public int Id { get; set; }
        [MaxLength]
        public string Description { get; set; }
        public double Price { get; set; }
        [MaxLength]
        public string Information { get; set; }
        public string Path { get; set; }
        public List<Review> Reviews { get; set; }
    }
}

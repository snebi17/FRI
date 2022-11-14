using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace SampleAPI.Entities
{
    public class Review
    {
        public int Id { get; set; }
        public string Title { get; set; }
        [MaxLength]
        public string Body { get; set; }
    }
}

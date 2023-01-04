using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace web.Models 
{
    public class Post 
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Id { get; set; }
        public string Title { get; set; }
        public string Body { get; set; }
        public User PostedBy { get; set; }
        public DateTime PostedAt { get; set; }
        public IEnumerable<Comment>? Comments { get; set; }
        [NotMapped]
        public IEnumerable<Reaction>? Reactions { get; set; }

        public Post() {
            PostedAt = DateTime.Now;
        }
    }
}
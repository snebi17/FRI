using System.ComponentModel.DataAnnotations.Schema;
namespace web.Models {
    public class Comment {
        public int Id { get; set; }
        public string Body { get; set; }
        public User PostedBy { get; set; }
        public DateTime PostedAt { get; set; }
        public IEnumerable<Comment>? Replies { get; set; }

        [NotMapped]
        public IEnumerable<Reaction>? Reactions { get; set; }
        
        public Comment() {
            PostedAt = DateTime.Now;
        }
    }
}
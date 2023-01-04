using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using vaja02.web.Models;
using vaja02.web.Services;

namespace web.Pages;

public class IndexModel : PageModel
{
    private readonly ILogger<IndexModel> _logger;

    public JsonFileProductService ProductService;
    public IEnumerable<Product> Products { get; set; }

    public IndexModel(ILogger<IndexModel> logger, JsonFileProductService productService)
    {
        _logger = logger;
        ProductService = productService;
    }

    public void OnGet()
    {
        Products = ProductService.GetProducts();
    }
}

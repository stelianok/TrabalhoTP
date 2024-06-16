class Product {
  public String name;
  public String description;
  public String category_id;
  public Double price;
  public Integer quantity;
  public String added_at;
  public String supplier_id;

  public Product(String name, String description, String category_id, Double price, Integer quantity, String added_at,
      String supplier_id) {
    this.name = name;
    this.description = description;
    this.category_id = category_id;
    this.price = price;
    this.quantity = quantity;
    this.added_at = added_at;
    this.supplier_id = supplier_id;
  }
}
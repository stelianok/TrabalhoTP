class ProductWithSupplierAndCategoryInfo {
  int id;
  String name;
  String description;
  String categoryName;
  double price;
  int quantity;
  String addedAt;
  String supplierName;

  public ProductWithSupplierAndCategoryInfo(int id, String name, String description, String categoryName, double price,
      int quantity,
      String addedAt, String supplierName) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.categoryName = categoryName;
    this.price = price;
    this.quantity = quantity;
    this.addedAt = addedAt;
    this.supplierName = supplierName;
  }
}
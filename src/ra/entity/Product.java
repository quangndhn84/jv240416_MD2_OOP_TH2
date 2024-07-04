package ra.entity;

import ra.imp.ProductImp;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.productId = inputProductId(scanner);
        this.productName = inputProductName(scanner);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.quantity = inputQuantity(scanner);
        this.descriptions = inputDescription(scanner);
        this.status = inputStatus(scanner);
    }

    public String inputProductId(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String productId = scanner.nextLine();
            if (productId.length() == 4) {
                //Kiểm tra trùng lặp
                boolean isExist = false;
                for (int i = 0; i < ProductImp.currentIndex; i++) {
                    if (ProductImp.arrProducts[i].getProductId().equals(productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Mã sản phẩm gồm 4 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            String productNameRegex = "\\w{6,50}";
            if (Pattern.matches(productNameRegex, productName)) {
                boolean isExist = false;
                for (int i = 0; i < ProductImp.currentIndex; i++) {
                    if (ProductImp.arrProducts[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm có từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputImportPrice(Scanner scanner) {
        System.out.println("Nhập vào giá nhập của sản phẩm:");
        do {
            float importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            } else {
                System.err.println("Giá nhập sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputExportPrice(Scanner scanner) {
        System.out.println("Nhập vào giá xuất của sản phẩm:");
        do {
            float exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice >= this.importPrice * 1.2F) {
                return exportPrice;
            } else {
                System.err.println("Giá xuất phải lớn hơn 20% so với giá nhập, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputQuantity(Scanner scanner) {
        System.out.println("Nhập vào số lượng sản phẩm:");
        do {
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0) {
                return quantity;
            } else {
                System.err.println("Số lượng sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm:");
        return scanner.nextLine();
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm:");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá nhập: %.1f - Giá xuất: %.1f\n",
                this.productId, this.productName, this.importPrice, this.exportPrice);
        System.out.printf("Lợi nhuận: %.1f - Số lượng: %d - Mô tả: %s - Trạng thái: %s\n",
                this.profit, this.quantity, this.descriptions, this.status ? "Đang bán" : "Không bán");
    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }

}

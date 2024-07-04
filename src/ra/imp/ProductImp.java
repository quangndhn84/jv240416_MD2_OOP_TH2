package ra.imp;

import ra.entity.Product;

import java.util.Scanner;

public class ProductImp {
    //Các biến toàn cục tĩnh sử dụng chung cho các đối tượng
    public static Product[] arrProducts = new Product[100];
    public static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************MENU*********************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductImp.inputListProduct(scanner);
                    break;
                case 2:
                    ProductImp.displayListProduct();
                    break;
                case 3:
                    ProductImp.calProfitOfProducts();
                    break;
                case 4:
                    ProductImp.sortProductByProfitDESC();
                    break;
                case 5:
                    ProductImp.staticticProductByPrice(scanner);
                    break;
                case 6:
                    ProductImp.searchProductByName(scanner);
                    break;
                case 7:
                    ProductImp.impotProducts(scanner);
                    break;
                case 8:
                    ProductImp.exportProduct(scanner);
                    break;
                case 9:
                    ProductImp.updateStatusOfProduct(scanner);
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-10");
            }
        } while (true);
    }

    public static void inputListProduct(Scanner scanner) {
        System.out.println("Nhập vào số sản phẩm cần nhập thông tin:");
        int numberOfProducts = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProducts; i++) {
            arrProducts[currentIndex] = new Product();
            arrProducts[currentIndex].inputData(scanner);
            currentIndex++;
        }
    }

    public static void displayListProduct() {
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].displayData();
        }
    }

    public static void calProfitOfProducts() {
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].calProfit();
        }
    }

    public static void sortProductByProfitDESC() {
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrProducts[i].getProfit() < arrProducts[j].getProfit()) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong các sản phẩm theo lợi nhuận giảm dần");
    }

    public static void staticticProductByPrice(Scanner scanner) {
        System.out.println("Nhập vào giá bắt đầu:");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào giá kết thúc:");
        float toPrice = Float.parseFloat(scanner.nextLine());
        int cntProduct = 0;
        System.out.printf("Các sản phẩm trong khoảng %.1f đến %.1f là:\n", fromPrice, toPrice);
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getExportPrice() >= fromPrice && arrProducts[i].getExportPrice() <= toPrice) {
                arrProducts[i].displayData();
                cntProduct++;
            }
        }
        System.out.printf("Có %d sản phẩm trong khoảng từ %.1f đến %.1f\n", cntProduct, fromPrice, toPrice);
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productName = scanner.nextLine();
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(productName.toLowerCase())) {
                arrProducts[i].displayData();
            }
        }
    }

    public static void impotProducts(Scanner scanner) {
        /*
         * 1. Sản phẩm đã tồn tại --> cộng số lượng
         * 2. Sản phẩm chưa tồn tại --> thêm sản phẩm vào arrProducts
         * */
        System.out.println("Nhập vào mã sản phẩm cần nhập:");
        String productId = scanner.nextLine();
        int indexImport = getIndexById(productId);
        if (indexImport >= 0) {
            //1. Đã tồn tại
            System.out.println("Nhập số lượng sản phẩm cần nhập:");
            int quantity = Integer.parseInt(scanner.nextLine());
            //-Cộng số lượng
            arrProducts[indexImport].setQuantity(arrProducts[indexImport].getQuantity() + quantity);
        } else {
            //2. Chưa tồn tại
            System.out.println("Sản phẩm chưa tồn tại, bạn có muốn nhập sản phẩm mới này hay không");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    arrProducts[currentIndex] = new Product();
                    arrProducts[currentIndex].inputData(scanner);
                    currentIndex++;
                    break;
                case 2:
                    System.out.println("Vui lòng chọn lại chức năng 7");
                    break;
            }
        }
    }

    public static int getIndexById(String productId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }


    public static void exportProduct(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần bán:");
        String productName = scanner.nextLine();
        int indexExport = getIndexByName(productName);
        if (indexExport >= 0) {
            //1. Đã tồn tại
            //1.1. Đủ số lượng để xuất
            //1.2. Không đủ số lượng để bán
            System.out.println("Nhập vào số lượng cần bán:");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (arrProducts[indexExport].getQuantity() >= quantity) {
                arrProducts[indexExport].setQuantity(arrProducts[indexExport].getQuantity() - quantity);
            } else {
                System.err.println("Số lượng sản phẩm không đủ để bán, vui lòng nhập thêm sản phẩm này");
            }
        } else {
            //2. Chưa tồn tại
            System.err.println("Tên sản phẩm không tồn tại, vui lòng chọn lại chức năng 8");
        }
    }

    public static int getIndexByName(String productName) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getProductName().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateStatusOfProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật trạng thái:");
        String productId = scanner.nextLine();
        int indexUpdate = getIndexById(productId);
        if (indexUpdate >= 0) {
            arrProducts[indexUpdate].setStatus(!arrProducts[indexUpdate].isStatus());
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }
}

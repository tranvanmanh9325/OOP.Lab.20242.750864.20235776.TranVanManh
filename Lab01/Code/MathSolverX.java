// Thêm thư viện
import javax.swing.*;

public class MathSolverX {

    // Hàm giải phương trình bậc nhất
    public static void phuong_trinh_bac_nhat() {

        // Khai báo các biến cần thiết
        double a = Double.parseDouble(JOptionPane.showInputDialog("Nhập a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Nhập b:"));

        // Khai báo biến result
        String result;
        if (a == 0) {
            result = (b == 0) ? "Phương trình vô số nghiệm" : "Phương trình vô nghiệm";
        }
        else {
            result = "Phương trình có nghiệm duy nhất là: " + (-b/a);
        }
        JOptionPane.showMessageDialog(null, result, "Nghiệm của phương trình bậc nhất", 
        JOptionPane.INFORMATION_MESSAGE);
    }

    // Hàm giải hệ phương trình bậc nhất
    public static void he_phuong_trinh_bac_nhat() {
        
        // Khai báo biến cần thiết
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a12:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Nhập b1:"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Nhập a22:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Nhập b2:"));

        // Khai báo định thức
        double D = a11*a22 - a21*a12;
        double D1 = b1*a22 - b2*a12;
        double D2 = a11*b2 - a21*b1;

        // Khai báo biến lưu kết quả
        String result;
        if (D == 0) {
            result = (D1 == 0 && D2 == 0) ? "Hệ phương trình vô số nghiệm" : "Hệ phương trình vô nghiệm";
        }
        else {
            result = "Nghiệm của hệ phương trình là:\n" + "x1 = " + String.format("%.2f", D1/D) + "\nx2 = " + 
            String.format("%.2f", D2/D);
        }
        JOptionPane.showMessageDialog(null, result, "Output", JOptionPane.INFORMATION_MESSAGE);
    }

    // Hàm giải phương trình bậc hai
    public static void phuong_trinh_bac_hai() {
        
        // Khai báo biến cần thiết
        double a = Double.parseDouble(JOptionPane.showInputDialog("Nhập a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Nhập b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Nhập c:"));

        // Khai báo biến result
        String result;
        if (a == 0) {
            result = "Đây không phải là phương trình bậc hai.";
            JOptionPane.showMessageDialog(null, result, "Output", JOptionPane.INFORMATION_MESSAGE);
            phuong_trinh_bac_nhat();
            return;
        }

        // Khai báo biến denta
        double denta = b*b - 4*a*c;
        if (denta > 0) {
            // Khai báo hai biến nghiệm
            double x1 = (-b + Math.sqrt(denta)) / (2 * a);
            double x2 = (-b - Math.sqrt(denta)) / (2 * a);
            result = "Phương trình có hai nghiệm phân biệt: x1 = " + String.format("%.2f", x1) + " và x2 = " + 
            String.format("%.2f", x2) + ".";
        }
        else if (denta == 0) {
            double x = -b / (2 * a);
            result = "Phương trình có nghiệm kép: x = " + String.format("%.2f", x) + ".";
        }
        else result = "Phương trình vô nghiệm.";
        JOptionPane.showMessageDialog(null, result, "Output", JOptionPane.INFORMATION_MESSAGE);
    }

    // Hàm main chính
    public static void main(String[] args) {
        
        // Khai báo biến
        String[] options = {"Phương trình bậc nhất", "Hệ phương trình bậc nhất", "Phương trình bậc hai", "Thoát"};
        int choice = JOptionPane.showOptionDialog(null, "Chọn phương trình cần giải:", 
        "Giải phương trình", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        
        // Chuyển chế độ
        switch (choice) {
            case 0:
                // Gọi hàm giải phương trình bậc nhất
                phuong_trinh_bac_nhat();
                break;
            case 1:
                // Gọi hàm giải hệ phương trình bậc nhất
                he_phuong_trinh_bac_nhat();
                break;
            case 2:
                // Gọi hàm giải phương trình bậc hai
                phuong_trinh_bac_hai();
                break;
            // Nếu lựa chọn khác thì thoát chương trình
            default:
                System.exit(0);
        }
    }
}
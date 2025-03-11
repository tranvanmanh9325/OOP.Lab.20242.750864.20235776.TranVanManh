// Import thư viện
import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        // Khai báo biến
        String input1, input2;

        // Hộp thoại nhập số vào
        input1 = JOptionPane.showInputDialog(null, "Vui lòng nhập số thứ nhất:", 
        "Số thứ nhất", JOptionPane.QUESTION_MESSAGE);
        input2 = JOptionPane.showInputDialog(null, "Vui lòng nhập số thứ hai:",
        "Số thứ hai", JOptionPane.QUESTION_MESSAGE);

        // Chuyển đổi String sang Int
        double so_thu_nhat = Double.parseDouble(input1);
        double so_thu_hai = Double.parseDouble(input2);

        // Tính tổng sum
        double sum = so_thu_nhat + so_thu_hai;

        // Tính hiệu difference
        double difference = so_thu_nhat - so_thu_hai;

        // Tính tích hai số
        double product = so_thu_nhat * so_thu_hai;

        // Tính thương hai số
        double quotient = so_thu_nhat / so_thu_hai;

        // Chuỗi lưu kết quả
        String result = String.format("Tổng = %.2f\nHiệu = %.2f\nTích  = %.2f\nThương = %s", sum, difference, product, 
        (so_thu_hai != 0) ? String.format("%.2f", quotient) : "Không xác định");

        // Hộp thoại hiện kết quả
        JOptionPane.showMessageDialog(null, result, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        
    }
}

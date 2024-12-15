import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 동아리 관리 프로그램 GUI 클래스
 *
 * <p>
 * 이 클래스는 Swing을 사용하여 동아리 관리 프로그램의 그래픽 사용자 인터페이스를 제공합니다.
 * 사용자와의 상호작용을 통해 동아리 등록, 조회, 데이터 저장 및 불러오기 기능을 수행합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.0.0
 * @since 2024-12-15
 *
 * @created 2024-12-15
 *
 * @changelog
 * <ul>
 *   <li>2024-12-15: 최초 생성 및 기본 GUI 구현, 동아리 등록, 조회, 저장, 불러오기 버튼 추가 및 기능 구현 (한승규)</li>
 * </ul>
 */
public class ClubManagementGUI {
    private JFrame frame;
    private ClubManager clubManager;

    /**
     * ClubManagementGUI 생성자
     *
     * <p>
     * 동아리 관리 프로그램의 GUI를 초기화합니다.
     * ClubManager 객체를 받아 데이터를 관리합니다.
     * </p>
     *
     * @param manager 동아리 관리 객체
     */
    public ClubManagementGUI(ClubManager manager) {
        this.clubManager = manager;
        frame = new JFrame("동아리 관리 프로그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // GUI 컴포넌트 설정
        setupComponents();

        frame.setVisible(true);
    }

    /**
     * GUI 컴포넌트를 초기화하고 설정합니다.
     *
     * <p>
     * 버튼 및 패널을 생성하고, 버튼 이벤트를 연결합니다.
     * </p>
     */
    private void setupComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // 버튼 추가
        JButton registerButton = new JButton("동아리 등록");
        JButton viewButton = new JButton("동아리 조회");
        JButton saveButton = new JButton("저장");
        JButton loadButton = new JButton("불러오기");

        // 버튼 이벤트 추가
        registerButton.addActionListener(this::registerClub);
        viewButton.addActionListener(this::viewClubs);
        saveButton.addActionListener(e -> clubManager.saveData());
        loadButton.addActionListener(e -> clubManager.loadData());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        frame.add(panel);
    }

    /**
     * 동아리 등록 이벤트 핸들러
     *
     * <p>
     * 사용자가 동아리 등록 버튼을 클릭하면 동아리 정보를 입력받아 등록합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     */
    private void registerClub(ActionEvent e) {
        String name = JOptionPane.showInputDialog("동아리 이름:");
        String advisor = JOptionPane.showInputDialog("지도 교수:");
        try {
            int memberCount = Integer.parseInt(JOptionPane.showInputDialog("회원 수:"));
            String purpose = JOptionPane.showInputDialog("설립 목적:");
            clubManager.registerClub(name, advisor, memberCount, purpose);
            JOptionPane.showMessageDialog(frame, "동아리가 등록되었습니다!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "회원 수는 숫자로 입력해야 합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 동아리 조회 이벤트 핸들러
     *
     * <p>
     * 사용자가 동아리 조회 버튼을 클릭하면 현재 등록된 동아리 목록을 표시합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     */
    private void viewClubs(ActionEvent e) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(clubManager.viewClubsAsString()); // ClubManager에서 viewClubsAsString 메서드 추가 필요
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "동아리 목록", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 프로그램의 진입점 메소드입니다.
     *
     * <p>
     * GUI를 실행하고 ClubManager 객체를 초기화합니다.
     * </p>
     *
     * @param args 명령행 인자를 전달받는 배열
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClubManagementGUI(new ClubManager()));
    }
}

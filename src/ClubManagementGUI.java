import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 동아리 관리 프로그램 GUI 클래스
 *
 * <p>
 * 이 클래스는 Swing을 사용하여 동아리 관리 프로그램의 그래픽 사용자 인터페이스를 제공합니다.
 * 사용자와의 상호작용을 통해 동아리 등록, 조회, 데이터 저장 및 불러오기 기능을 수행합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.6.0
 * @since 2024-12-15
 *
 * @created 2024-12-15
 * @lastModified 2024-12-24
 *
 * @changelog
 * <ul>
 *   <li>2024-12-15: 최초 생성 및 기본 GUI 구현, 동아리 등록, 조회, 저장, 불러오기 버튼 추가 (한승규)</li>
 *   <li>2024-12-16: 프로그램 종료 버튼 추가 및 GUI 개선 (한승규)</li>
 *   <li>2024-12-18: 활동 보고서 작성 및 조회 기능 추가, GUI 확장 (한승규)</li>
 *   <li>2024-12-19: 버튼 레이아웃 변경, GUI 배치 개선 (한승규)</li>
 *   <li>2024-12-21: 활동 보고서 검색 기능 추가 (한승규)</li>
 *   <li>2024-12-24: 보고서 통계 기능 추가 및 버튼 연동 (한승규)</li>
 * </ul>
 */
public class ClubManagementGUI {
    private JFrame frame; // GUI 메인 프레임
    private ClubManager clubManager; // 동아리 관리 객체

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
        frame.setSize(600, 500);

        // GUI 컴포넌트 설정
        setupComponents();

        frame.setVisible(true);
    }

    /**
     * GUI 컴포넌트를 초기화하고 설정합니다.
     *
     * <p>
     * 동아리 등록, 조회, 활동 보고서 작성, 검색, 데이터 저장 및 불러오기 버튼을 생성하고,
     * 각 버튼에 이벤트를 연결합니다. 또한, 보고서 통계 버튼을 추가하여 활동 보고서에 대한 통계를 확인할 수 있는 기능을 제공합니다.
     * GridBagLayout을 사용하여 버튼의 위치를 조정하고, 사용자 인터페이스를 구성합니다.
     * </p>
     *
     * @created 2024-12-15
     * @lastModified 2024-12-24
     *
     * @changelog
     * <ul>
     *   <li>2024-12-15: 기본 GUI 버튼 구성 (한승규)</li>
     *   <li>2024-12-21: 활동 보고서 검색 버튼 추가 (한승규)</li>
     *   <li>2024-12-24: 보고서 통계 버튼 추가 (한승규)</li>
     * </ul>
     */
    private void setupComponents() {
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 버튼 간 여백 설정

        // 버튼 추가
        JButton registerButton = new JButton("동아리 등록");
        JButton viewButton = new JButton("동아리 목록 조회");
        JButton addReportButton = new JButton("활동 보고서 작성");
        JButton viewReportButton = new JButton("활동 보고서 조회");
        JButton saveButton = new JButton("데이터 저장");
        JButton loadButton = new JButton("데이터 불러오기");
        JButton exitButton = new JButton("프로그램 종료");
        JButton searchReportButton = new JButton("활동 보고서 검색");
        JButton viewStatsButton = new JButton("보고서 통계");

        // 버튼 이벤트 추가
        registerButton.addActionListener(this::registerClub);
        viewButton.addActionListener(this::viewClubs);
        addReportButton.addActionListener(this::addReport);
        viewReportButton.addActionListener(this::viewReports);
        saveButton.addActionListener(e -> clubManager.saveData());
        loadButton.addActionListener(e -> clubManager.loadData());
        exitButton.addActionListener(e -> System.exit(0));
        searchReportButton.addActionListener(this::searchReports);
        viewStatsButton.addActionListener(this::viewStats);

        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(registerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        frame.add(viewButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        frame.add(addReportButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        frame.add(viewReportButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        frame.add(saveButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        frame.add(loadButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        frame.add(searchReportButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        frame.add(viewStatsButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(exitButton, gbc);
    }

    /**
     * 동아리 등록 이벤트 핸들러
     *
     * <p>
     * 사용자가 동아리 등록 버튼을 클릭하면 동아리 정보를 입력받아 등록합니다.
     * 입력값에 숫자가 아닌 값이 들어갈 경우 오류 메시지를 표시합니다.
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
     * 동아리 목록 조회 이벤트 핸들러
     *
     * <p>
     * 사용자가 동아리 목록 조회 버튼을 클릭하면 현재 등록된 동아리 목록을 표시합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     */
    private void viewClubs(ActionEvent e) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(clubManager.viewClubsAsString()); // ClubManager에서 viewClubsAsString 메서드 필요
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "동아리 목록", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 활동 보고서 작성 이벤트 핸들러
     *
     * <p>
     * 사용자가 활동 보고서 작성 버튼을 클릭하면 활동 내용을 입력받아 ClubManager에 등록합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     */
    private void addReport(ActionEvent e) {
        String clubName = JOptionPane.showInputDialog("동아리 이름:");
        String activityContent = JOptionPane.showInputDialog("활동 내용:");
        String author = JOptionPane.showInputDialog("작성자 이름:");
        String location = JOptionPane.showInputDialog("활동 위치:");
        String result = JOptionPane.showInputDialog("활동 결과:");
        String date = JOptionPane.showInputDialog("활동 날짜 (YYYY-MM-DD):");

        clubManager.addDetailedReport(clubName, activityContent, author, location, result, date);
        JOptionPane.showMessageDialog(frame, "활동 보고서가 작성되었습니다!");
    }


    /**
     * 활동 보고서 조회 이벤트 핸들러
     *
     * <p>
     * 사용자가 활동 보고서 조회 버튼을 클릭하면 현재 작성된 활동 보고서 목록을 표시합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     */
    private void viewReports(ActionEvent e) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(clubManager.viewReportsAsString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "활동 보고서 목록", JOptionPane.INFORMATION_MESSAGE);
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

    /**
     * 활동 보고서 검색 이벤트 핸들러
     *
     * <p>
     * 사용자가 활동 보고서 검색 버튼을 클릭하면 검색 방식을 선택하고, 키워드 또는 날짜로 검색합니다.
     * 검색 결과를 텍스트 영역에 표시하며, 결과가 없을 경우 적절한 메시지를 출력합니다.
     * </p>
     *
     * @param e ActionEvent 객체
     * @created 2024-12-21
     */
    private void searchReports(ActionEvent e) {
        String[] options = {"키워드로 검색", "날짜로 검색"};
        int choice = JOptionPane.showOptionDialog(frame, "검색 방식을 선택하세요:", "활동 보고서 검색",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        List<ActivityReport> results = new ArrayList<>();
        if (choice == 0) {
            String keyword = JOptionPane.showInputDialog("검색할 키워드를 입력하세요:");
            results = clubManager.searchReportsByKeyword(keyword);
        } else if (choice == 1) {
            String date = JOptionPane.showInputDialog("검색할 날짜를 입력하세요 (예: 2024-12-19):");
            results = clubManager.searchReportsByDate(date);
        }

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "검색 결과가 없습니다.", "검색 결과", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JTextArea textArea = new JTextArea(20, 40);
            for (ActivityReport report : results) {
                textArea.append(report.getReportDetails() + "\n");
            }
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "검색 결과", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewStats(ActionEvent e) {
        StringBuilder stats = new StringBuilder();
        stats.append("총 보고서 수: ").append(clubManager.getTotalReportsCount()).append("\n");

        String clubName = JOptionPane.showInputDialog("특정 동아리 이름을 입력하세요 (선택 사항):");
        if (clubName != null && !clubName.isEmpty()) {
            stats.append("동아리 '").append(clubName).append("'의 보고서 수: ")
                    .append(clubManager.getReportsByClub(clubName)).append("\n");
        }

        String startDate = JOptionPane.showInputDialog("시작 날짜를 입력하세요 (YYYY-MM-DD):");
        String endDate = JOptionPane.showInputDialog("종료 날짜를 입력하세요 (YYYY-MM-DD):");
        if (startDate != null && endDate != null) {
            List<ActivityReport> filteredReports = clubManager.getReportsInDateRange(startDate, endDate);
            stats.append("기간 ").append(startDate).append(" ~ ").append(endDate).append(" 동안 보고서 수: ")
                    .append(filteredReports.size()).append("\n");
        }

        JTextArea textArea = new JTextArea(stats.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "보고서 통계", JOptionPane.INFORMATION_MESSAGE);
    }
}
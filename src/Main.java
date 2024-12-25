import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 * 동아리 관리 프로그램의 메인 클래스입니다.
 *
 * <p>
 * 이 클래스는 프로그램의 진입점 역할을 하며, GUI와 콘솔을 통해 사용자와 상호작용하는 메뉴를 제공합니다.
 * 프로그램 시작과 종료 시 데이터를 자동으로 저장하고 불러오는 기능을 포함합니다.
 * GUI는 Swing을 사용하며, 콘솔 인터페이스는 기본적인 메뉴 기반으로 동작합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.4.0
 * @since 2024-12-04
 *
 * @created 2024-12-04
 * @lastModified 2024-12-22
 *
 * @changelog
 * <ul>
 *   <li>2024-12-04: 최초 생성 (한승규)</li>
 *   <li>2024-12-05: 메소드 세부 구현 (한승규)</li>
 *   <li>2024-12-08: 프로그램 시작 및 종료 시 데이터 자동 저장/불러오기 추가 (한승규)</li>
 *   <li>2024-12-15: Swing GUI 통합 및 SwingUtilities 활용 (한승규)</li>
 *   <li>2024-12-22: 활동 보고서 작성 기능 개선 및 날짜 입력 지원 추가 (한승규)</li>
 * </ul>
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in); // 사용자 입력용 스캐너
    private static final ClubManager clubManager = new ClubManager(); // 동아리 관리 객체

    /**
     * 프로그램의 진입점 메소드입니다.
     *
     * <p>
     * 프로그램 시작 시 데이터를 불러오고, Swing GUI를 실행합니다. 종료 시 데이터를 저장합니다.
     * 메인 메뉴를 통해 사용자 입력에 따라 동작을 수행합니다.
     * </p>
     *
     * @param args 명령행 인자를 전달받는 배열
     *
     * @created 2024-12-04
     * @lastModified 2024-12-22
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 최초 생성 (한승규)</li>
     *   <li>2024-12-08: 데이터 자동 저장 및 불러오기 추가 (한승규)</li>
     *   <li>2024-12-15: Swing GUI 통합 및 실행 (한승규)</li>
     *   <li>2024-12-22: 활동 보고서 작성 기능 개선 (한승규)</li>
     * </ul>
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClubManagementGUI(clubManager)); // GUI 실행
        clubManager.loadData(); // 프로그램 시작 시 데이터 불러오기
        boolean isRunning = true;

        System.out.println("==== 동아리 관리 프로그램 ====");

        // 메인 루프
        while (isRunning) {
            printMenu(); // 메뉴 출력
            int choice = getUserChoice(); // 사용자 선택 받기

            // 선택에 따른 동작 수행
            switch (choice) {
                case 1 -> registerClub();       // 동아리 등록
                case 2 -> viewClubs();          // 동아리 목록 조회
                case 3 -> addReport();          // 활동 보고서 작성
                case 4 -> viewReports();        // 활동 보고서 조회
                case 5 -> saveData();           // 데이터 저장
                case 6 -> loadData();           // 데이터 불러오기
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    isRunning = false;          // 프로그램 종료
                }
                default -> System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
        clubManager.saveData(); // 프로그램 종료 시 데이터 저장
        scanner.close(); // 스캐너 종료
    }

    /**
     * 메인 메뉴를 출력합니다.
     */
    private static void printMenu() {
        System.out.println("\n[메인 메뉴]");
        System.out.println("1. 동아리 등록");
        System.out.println("2. 동아리 목록 조회");
        System.out.println("3. 활동 보고서 작성");
        System.out.println("4. 활동 보고서 조회");
        System.out.println("5. 데이터 저장");
        System.out.println("6. 데이터 불러오기");
        System.out.println("0. 프로그램 종료");
        System.out.print("선택: ");
    }

    /**
     * 사용자로부터 메뉴 선택을 입력받습니다.
     *
     * @return 선택된 메뉴 번호
     */
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1; // 잘못된 입력
        }
    }

    /**
     * 동아리를 등록하는 메소드입니다.
     *
     * <p>
     * 사용자로부터 동아리 이름, 지도 교수, 회원 수, 설립 목적을 입력받아
     * ClubManager를 통해 등록합니다.
     * </p>
     */
    private static void registerClub() {
        System.out.println("[동아리 등록]");
        System.out.print("동아리 이름: ");
        String name = scanner.nextLine();

        System.out.print("지도 교수: ");
        String advisor = scanner.nextLine();

        System.out.print("회원 수: ");
        int memberCount = Integer.parseInt(scanner.nextLine());

        System.out.print("설립 목적: ");
        String purpose = scanner.nextLine();

        clubManager.registerClub(name, advisor, memberCount, purpose);
        System.out.println("동아리가 등록되었습니다.");
    }

    /**
     * 동아리 목록을 조회하는 메소드입니다.
     *
     * <p>
     * 등록된 동아리 목록을 ClubManager를 통해 출력합니다.
     * </p>
     */
    private static void viewClubs() {
        System.out.println("[동아리 목록 조회]");
        clubManager.viewClubs();
    }

    /**
     * 활동 보고서를 작성하는 메소드입니다.
     *
     * <p>
     * 사용자로부터 동아리 이름과 활동 내용을 입력받아
     * ClubManager를 통해 보고서를 추가합니다.
     * </p>
     */
    private static void addReport() {
        System.out.println("[활동 보고서 작성]");
        System.out.print("동아리 이름: ");
        String clubName = scanner.nextLine();

        System.out.print("활동 내용: ");
        String activityContent = scanner.nextLine();

        System.out.print("작성자: ");
        String author = scanner.nextLine();

        System.out.print("활동 위치: ");
        String location = scanner.nextLine();

        System.out.print("활동 결과: ");
        String result = scanner.nextLine();

        System.out.print("활동 날짜 (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        if (date == null || date.isBlank()) {
            date = java.time.LocalDate.now().toString(); // 현재 날짜로 설정
        }

        clubManager.addDetailedReport(clubName, activityContent, author, location, result, date);
        System.out.println("활동 보고서가 작성되었습니다.");
    }

    /**
     * 활동 보고서를 조회하는 메소드입니다.
     *
     * <p>
     * 작성된 활동 보고서를 ClubManager를 통해 출력합니다.
     * </p>
     */
    private static void viewReports() {
        System.out.println("[활동 보고서 조회]");
        String reports = clubManager.viewReportsAsString();
        System.out.println(reports);
    }

    /**
     * 데이터를 파일에 저장하는 메소드입니다.
     *
     * <p>
     * ClubManager를 통해 동아리 및 보고서 데이터를 저장합니다.
     * </p>
     */
    private static void saveData() {
        System.out.println("[데이터 저장]");
        clubManager.saveData();
    }

    /**
     * 데이터를 파일에서 불러오는 메소드입니다.
     *
     * <p>
     * ClubManager를 통해 동아리 및 보고서 데이터를 불러옵니다.
     * </p>
     */
    private static void loadData() {
        System.out.println("[데이터 불러오기]");
        clubManager.loadData();
    }
}
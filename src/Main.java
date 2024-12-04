import java.util.Scanner;

/**
 * 동아리 관리 프로그램의 메인 클래스입니다.
 *
 * <p>
 * 이 클래스는 프로그램의 진입점 역할을 하며, 사용자와 상호작용하는 메뉴를 제공합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.0
 * @since 2024-12-04
 *
 * @created 2024-12-04
 * @lastModified 2024-12-04
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in); // 사용자 입력용
    private static final ClubManager clubManager = new ClubManager(); // 동아리 관리 객체

    public static void main(String[] args) {
        boolean isRunning = true;

        System.out.println("==== 동아리 관리 프로그램 ====");

        // 메인 루프
        while (isRunning) {
            printMenu(); // 메뉴 출력
            int choice = getUserChoice(); // 사용자 선택 받기

            // 메뉴 선택
            switch (choice) {
                case 1 -> registerClub();       // 동아리 등록
                // 추가 예정
                case 0 -> {
                    System.out.println("프로그램을 종료");
                    isRunning = false;          // 프로그램 종료.
                }
                default -> System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }

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

}
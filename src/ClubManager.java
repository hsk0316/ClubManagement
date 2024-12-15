import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 동아리 관리 프로그램
 *
 * <p>
 * 이 클래스는 동아리와 활동 보고서를 관리하는 역할을 합니다.
 * 동아리 등록, 조회, 활동 보고서 작성 및 조회, 데이터 저장/불러오기 기능을 제공합니다.
 * GUI를 위한 텍스트 데이터를 반환하는 메서드도 추가되었습니다.
 * </p>
 *
 * @author 한승규
 * @version 1.3.0
 * @since 2024-12-04
 *
 * @created 2024-12-01
 * @lastModified 2024-12-15
 *
 * @changelog
 * <ul>
 *   <li>2024-12-01: 최초 생성 (한승규)</li>
 *   <li>2024-12-04: 메인 메소드와 기본 클래스 구조 추가 (한승규)</li>
 *   <li>2024-12-07: 동아리 및 보고서 관리 리스트 추가, 관련 메소드 구현 (한승규)</li>
 *   <li>2024-12-08: 데이터 저장 및 불러오기 메서드 구현 (한승규)</li>
 *   <li>2024-12-15: GUI를 위한 문자열 반환 메서드 추가 (한승규)</li>
 * </ul>
 */
public class ClubManager {
    private final List<Club> clubs = new ArrayList<>(); // 동아리 목록
    private final List<ActivityReport> reports = new ArrayList<>(); // 활동 보고서 목록

    private static final String CLUBS_FILE = "clubs.dat"; // 동아리 데이터 저장 파일
    private static final String REPORTS_FILE = "reports.dat"; // 보고서 데이터 저장 파일

    /**
     * 프로그램의 진입점 메소드입니다.
     *
     * <p>
     * 이 메소드는 동아리 관리 프로그램의 메인 흐름을 제어하며,
     * 사용자로부터 입력을 받아 동아리 등록, 조회, 활동 보고서 작성 등의 작업을 수행합니다.
     * </p>
     *
     * @param args 명령행 인자를 전달받는 배열
     *
     * @created 2024-12-01
     * @lastModified 2024-12-04
     *
     * @changelog
     * <ul>
     *   <li>2024-12-01: 최초 생성 (한승규)</li>
     *   <li>2024-12-04: 기본 프로그램 흐름 작성 (한승규)</li>
     * </ul>
     */
    public static void main(String[] args) {
        System.out.println("동아리 관리 프로그램");
    }

    /**
     * 동아리 등록 메소드입니다.
     *
     * <p>
     * 새로운 동아리 객체를 생성하고 내부 데이터 리스트에 추가합니다.
     * 동아리 이름, 지도 교수, 회원 수, 설립 목적을 입력받아 등록합니다.
     * </p>
     *
     * @param name 동아리 이름
     * @param advisor 지도 교수
     * @param memberCount 회원 수
     * @param purpose 설립 목적
     *
     * @created 2024-12-04
     * @lastModified 2024-12-15
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 동아리 등록 메소드 추가 (한승규)</li>
     *   <li>2024-12-15: 데이터 관리 리스트 업데이트 (한승규)</li>
     * </ul>
     */
    public void registerClub(String name, String advisor, int memberCount, String purpose) {
        clubs.add(new Club(name, advisor, memberCount, purpose));
        System.out.println("동아리가 등록되었습니다: " + name);
    }

    /**
     * 등록된 동아리 목록을 조회합니다.
     *
     * <p>
     * 현재 등록된 모든 동아리 정보를 출력합니다.
     * 등록된 동아리가 없으면 적절한 메시지를 표시합니다.
     * </p>
     *
     * @created 2024-12-04
     * @lastModified 2024-12-07
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 동아리 조회 메소드 추가 (한승규)</li>
     *   <li>2024-12-07: 리스트 출력 로직 추가 (한승규)</li>
     * </ul>
     */
    public void viewClubs() {
        if (clubs.isEmpty()) {
            System.out.println("등록된 동아리가 없습니다.");
            return;
        }
        System.out.println("==== 동아리 목록 ====");
        for (Club club : clubs) {
            System.out.println(club.getDetails());
        }
    }

    /**
     * 활동 보고서를 작성합니다.
     *
     * <p>
     * 특정 동아리의 활동 내용을 작성하여 보고서 리스트에 추가합니다.
     * </p>
     *
     * @param clubName 동아리 이름
     * @param activityContent 활동 내용
     *
     * @created 2024-12-04
     * @lastModified 2024-12-07
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 활동 보고서 작성 메소드 추가 (한승규)</li>
     *   <li>2024-12-07: 리스트에 보고서 추가 로직 작성 (한승규)</li>
     * </ul>
     */
    public void addReport(String clubName, String activityContent) {
        for (Club club : clubs) {
            if (club.getName().equals(clubName)) {
                reports.add(new ActivityReport(clubName, activityContent));
                System.out.println("활동 보고서가 작성되었습니다.");
                return;
            }
        }
        System.out.println("동아리를 찾을 수 없습니다: " + clubName);
    }

    /**
     * 작성된 활동 보고서를 조회합니다.
     *
     * <p>
     * 현재 작성된 모든 활동 보고서를 출력합니다.
     * 보고서가 없으면 적절한 메시지를 표시합니다.
     * </p>
     *
     * @created 2024-12-04
     * @lastModified 2024-12-07
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 활동 보고서 조회 메소드 추가 (한승규)</li>
     *   <li>2024-12-07: 리스트 출력 로직 추가 (한승규)</li>
     * </ul>
     */
    public void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("작성된 활동 보고서가 없습니다.");
        } else {
            System.out.println("==== 활동 보고서 목록 ====");
            for (ActivityReport report : reports) {
                System.out.println(report.getReportDetails()); // `getReportDetails` 메서드 출력
            }
        }
    }

    /**
     * 동아리 및 활동 보고서를 저장하는 메서드입니다.
     *
     * <p>
     * 동아리와 활동 보고서 데이터를 각각 파일에 직렬화하여 저장합니다.
     * 데이터는 프로그램 종료 시 손실되지 않도록 로컬 파일로 저장됩니다.
     * </p>
     *
     * @created 2024-12-08
     * @lastModified 2024-12-15
     *
     * @changelog
     * <ul>
     *   <li>2024-12-08: 데이터 저장 메서드 구현 (한승규)</li>
     *   <li>2024-12-15: GUI 연동 추가 (한승규)</li>
     * </ul>
     */
    public void saveData() {
        try (ObjectOutputStream clubOut = new ObjectOutputStream(new FileOutputStream(CLUBS_FILE));
             ObjectOutputStream reportOut = new ObjectOutputStream(new FileOutputStream(REPORTS_FILE))) {

            clubOut.writeObject(clubs);
            reportOut.writeObject(reports);
            System.out.println("데이터가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 저장된 동아리 및 활동 보고서를 불러오는 메서드입니다.
     *
     * <p>
     * 파일에서 데이터를 읽어와 프로그램 내 데이터 리스트를 초기화합니다.
     * 데이터가 없는 경우 초기 상태로 유지됩니다.
     * </p>
     *
     * @created 2024-12-08
     * @lastModified 2024-12-08
     *
     * @changelog
     * <ul>
     *   <li>2024-12-08: 데이터 불러오기 메서드 구현 (한승규)</li>
     * </ul>
     */
    public void loadData() {
        try (ObjectInputStream clubIn = new ObjectInputStream(new FileInputStream(CLUBS_FILE));
             ObjectInputStream reportIn = new ObjectInputStream(new FileInputStream(REPORTS_FILE))) {

            clubs.clear();
            reports.clear();
            clubs.addAll((List<Club>) clubIn.readObject());
            reports.addAll((List<ActivityReport>) reportIn.readObject());
            System.out.println("데이터가 성공적으로 불러와졌습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("저장된 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("데이터 불러오기 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 등록된 동아리 목록을 문자열로 반환합니다.
     *
     * <p>
     * GUI에서 사용하기 위한 문자열 형태의 동아리 목록을 반환합니다.
     * 등록된 동아리가 없으면 '등록된 동아리가 없습니다.' 메시지를 반환합니다.
     * </p>
     *
     * @return 등록된 동아리 목록 문자열
     *
     * @created 2024-12-15
     * @lastModified 2024-12-15
     *
     * @changelog
     * <ul>
     *   <li>2024-12-15: GUI를 위한 메서드 추가 (한승규)</li>
     * </ul>
     */
    public String viewClubsAsString() {
        if (clubs.isEmpty()) return "등록된 동아리가 없습니다.";
        StringBuilder sb = new StringBuilder("==== 동아리 목록 ====\n");
        for (Club club : clubs) {
            sb.append(club.getDetails()).append("\n");
        }
        return sb.toString();
    }
}
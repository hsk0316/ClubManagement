import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;


/**
 * 동아리 관리 프로그램
 *
 * <p>
 * 이 클래스는 동아리와 활동 보고서를 관리하는 역할을 합니다.
 * 동아리 등록, 조회, 활동 보고서 작성 및 조회, 데이터 저장/불러오기,
 * 활동 보고서 검색, 보고서 통계 및 특정 기간 내 검색 기능을 제공합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.9.0
 * @since 2024-12-04
 *
 * @created 2024-12-01
 * @lastModified 2024-12-25
 *
 * @changelog
 * <ul>
 *   <li>2024-12-01: 최초 생성 (한승규)</li>
 *   <li>2024-12-04: 메인 메소드와 기본 클래스 구조 추가 (한승규)</li>
 *   <li>2024-12-07: 동아리 및 보고서 관리 리스트 추가, 관련 메소드 구현 (한승규)</li>
 *   <li>2024-12-08: 데이터 저장 및 불러오기 메서드 구현 (한승규)</li>
 *   <li>2024-12-15: GUI를 위한 문자열 반환 메서드 추가 (한승규)</li>
 *   <li>2024-12-18: 활동 보고서 조회 기능 강화 및 GUI 연동 메서드 추가 (한승규)</li>
 *   <li>2024-12-20: 예외 처리 강화 (한승규)</li>
 *   <li>2024-12-21: 활동 보고서 검색 기능 추가 (한승규)</li>
 *   <li>2024-12-24: 보고서 통계 및 특정 기간 검색 기능 추가 (한승규)</li>
 *   <li>2024-12-25: 활동 보고서 HashMap 구조 적용 및 메서드 수정 (한승규)</li>
 * </ul>
 */
public class ClubManager {
    private final List<Club> clubs = new ArrayList<>(); // 동아리 목록
    private final HashMap<String, List<ActivityReport>> reports = new HashMap<>(); // 활동 보고서 목록

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
     * @lastModified 2024-12-25
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 동아리 등록 메소드 추가 (한승규)</li>
     *   <li>2024-12-15: 데이터 관리 리스트 업데이트 (한승규)</li>
     *   <li>2024-12-25: 메소드 호환 수정 (한승규)</li>
     * </ul>
     */
    public void registerClub(String name, String advisor, int memberCount, String purpose) {
        for (Club club : clubs) {
            if (club.getName().equals(name)) {
                System.out.println("이미 등록된 동아리입니다: " + name);
                return;
            }
        }
        clubs.add(new Club(name, advisor, memberCount, purpose));
        reports.put(name, new ArrayList<>()); // 새로운 동아리의 활동 보고서 리스트 초기화
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
     * @param author 작성자
     * @param location 활동 위치
     * @param result 활동 결과
     * @param date 작성 날짜 (YYYY-MM-DD)
     * @created 2024-12-07
     * @lastModified 2024-12-25
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 활동 보고서 작성 메소드 추가 (한승규)</li>
     *   <li>2024-12-07: 리스트에 보고서 추가 로직 작성 (한승규)</li>
     *   <li>2024-12-25: HashMap 구조로 수정 (한승규)</li>
     * </ul>
     */
    public void addDetailedReport(String clubName, String activityContent, String author, String location, String result, String date) {
        if (!reports.containsKey(clubName)) {
            System.out.println("동아리를 찾을 수 없습니다: " + clubName);
            return;
        }
        ActivityReport report = new ActivityReport(clubName, activityContent, author, location, result, date);
        reports.get(clubName).add(report); // 동아리별로 보고서 추가
        System.out.println("활동 보고서가 작성되었습니다.");
    }

    /**
     * 특정 동아리의 활동 보고서를 조회합니다.
     *
     * <p>
     * 특정 동아리에 작성된 활동 보고서를 출력합니다.
     * 동아리 이름이 잘못되었거나 보고서가 없으면 적절한 메시지를 출력합니다.
     * </p>
     *
     * @param clubName 조회할 동아리 이름
     *
     * @created 2024-12-25
     */
    public void viewReportsByClub(String clubName) {
        List<ActivityReport> clubReports = reports.get(clubName);
        if (clubReports == null || clubReports.isEmpty()) {
            System.out.println("해당 동아리에 대한 보고서가 없습니다: " + clubName);
            return;
        }
        System.out.println("==== " + clubName + " 보고서 목록 ====");
        for (ActivityReport report : clubReports) {
            System.out.println(report.getReportDetails());
        }
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
     * @lastModified 2024-12-25
     *
     * @changelog
     * <ul>
     *   <li>2024-12-04: 활동 보고서 조회 메소드 추가 (한승규)</li>
     *   <li>2024-12-07: 리스트 출력 로직 추가 (한승규)</li>
     *   <li>2024-12-07: 메소드와 호환 가능하게 변경 (한승규)</li>
     * </ul>
     */
    public void viewAllReports() {
        if (reports.isEmpty()) {
            System.out.println("작성된 활동 보고서가 없습니다.");
            return;
        }
        for (String clubName : reports.keySet()) {
            viewReportsByClub(clubName);
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
     * @lastModified 2024-12-25
     *
     * @changelog
     * <ul>
     *   <li>2024-12-08: 데이터 저장 메서드 구현 (한승규)</li>
     *   <li>2024-12-15: GUI 연동 추가 (한승규)</li>
     *   <li>2024-12-20: 예외처리 (한승규)</li>
     *   <li>2024-12-25: 예외처리 (한승규)</li>
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
            JOptionPane.showMessageDialog(null, "데이터 저장 중 오류가 발생했습니다.\n오류 내용: " + e.getMessage(),
                    "저장 실패", JOptionPane.ERROR_MESSAGE);
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
     * @lastModified 2024-12-20
     *
     * @changelog
     * <ul>
     *   <li>2024-12-08: 데이터 불러오기 메서드 구현 (한승규)</li>
     *   <li>2024-12-20: 예외처리 (한승규)</li>
     * </ul>
     */
    public void loadData() {
        try (ObjectInputStream clubIn = new ObjectInputStream(new FileInputStream(CLUBS_FILE));
             ObjectInputStream reportIn = new ObjectInputStream(new FileInputStream(REPORTS_FILE))) {
            clubs.clear();
            reports.clear();
            clubs.addAll((List<Club>) clubIn.readObject());
            reports.putAll((HashMap<String, List<ActivityReport>>) reportIn.readObject());
            System.out.println("데이터가 성공적으로 불러와졌습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("저장된 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("데이터 불러오기 중 오류가 발생했습니다: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "데이터 불러오기 중 오류가 발생했습니다.\n오류 내용: " + e.getMessage(),
                    "불러오기 실패", JOptionPane.ERROR_MESSAGE);
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
     *   <li>2024-12-15: 등록된 동아리 목록 GUI를 위한 메서드 추가 (한승규)</li>
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

    /**
     * 등록된 활동 보고서를 문자열로 반환합니다.
     *
     * <p>
     * GUI에서 사용하기 위한 문자열 형태의 활동 보고서 목록을 반환합니다.
     * 등록된 보고서가 없으면 '작성된 활동 보고서가 없습니다.' 메시지를 반환합니다.
     * </p>
     *
     * @return 활동 보고서 목록 문자열
     *
     * @created 2024-12-18
     * @lastModified 2024-12-18
     *
     * @changelog
     * <ul>
     *     <li>2024-12-18: 등록된 활동 보고서 GUI를 위한 메서드 추가 (한승규)</li>
     * </ul>
     */
    public String viewReportsAsString() {
        if (reports.isEmpty()) return "작성된 활동 보고서가 없습니다.";

        StringBuilder sb = new StringBuilder("==== 활동 보고서 목록 ====\n");
        // HashMap의 모든 값(List<ActivityReport>)을 순회
        for (Map.Entry<String, List<ActivityReport>> entry : reports.entrySet()) {
            String clubName = entry.getKey(); // 동아리 이름
            List<ActivityReport> reportList = entry.getValue(); // 해당 동아리의 보고서 리스트

            sb.append("동아리: ").append(clubName).append("\n"); // 동아리 이름 추가
            for (ActivityReport report : reportList) {
                sb.append("  - ").append(report.getReportDetails()).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * 키워드로 활동 보고서를 검색합니다.
     *
     * <p>
     * 활동 보고서 내용에 특정 키워드가 포함된 보고서를 반환합니다.
     * 검색 결과는 리스트 형태로 반환되며, 결과가 없을 경우 빈 리스트를 반환합니다.
     * </p>
     *
     * @param keyword 검색할 키워드
     * @return 키워드가 포함된 활동 보고서 리스트
     *
     * @created 2024-12-21
     *
     * @changelog
     * <ul>
     *   <li>2024-12-21: 키워드 검색 기능 추가 (한승규)</li>
     * </ul>
     */
    public List<ActivityReport> searchReportsByKeyword(String keyword) {
        List<ActivityReport> results = new ArrayList<>();
        for (List<ActivityReport> reportList : reports.values()) {
            for (ActivityReport report : reportList) {
                if (report.getContent().contains(keyword)) {
                    results.add(report);
                }
            }
        }
        return results;
    }

    /**
     * 날짜로 활동 보고서를 검색합니다.
     *
     * <p>
     * 특정 날짜에 작성된 활동 보고서를 반환합니다.
     * 검색 결과는 리스트 형태로 반환되며, 결과가 없을 경우 빈 리스트를 반환합니다.
     * </p>
     *
     * @param date 검색할 날짜 (예: "2024-12-21")
     * @return 해당 날짜에 작성된 활동 보고서 리스트
     *
     * @created 2024-12-21
     *
     * @changelog
     * <ul>
     *   <li>2024-12-21: 날짜 검색 기능 추가 (한승규)</li>
     * </ul>
     */
    public List<ActivityReport> searchReportsByDate(String date) {
        List<ActivityReport> results = new ArrayList<>();
        for (List<ActivityReport> reportList : reports.values()) {
            for (ActivityReport report : reportList) {
                if (report.getDate().equals(date)) {
                    results.add(report);
                }
            }
        }
        return results;
    }

    /**
     * 검색 결과를 문자열로 반환합니다.
     *
     * <p>
     * 검색 결과를 텍스트로 표시하기 위해 리스트 형태의 활동 보고서를 문자열로 변환합니다.
     * </p>
     *
     * @param reports 검색된 활동 보고서 리스트
     * @return 검색 결과 문자열
     *
     * @created 2024-12-21
     */
    public String formatReportSearchResults(List<ActivityReport> reports) {
        if (reports.isEmpty()) {
            return "검색 결과가 없습니다.";
        }
        StringBuilder sb = new StringBuilder("==== 검색 결과 ====\n");
        for (ActivityReport report : reports) {
            sb.append(report.getReportDetails()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 총 활동 보고서 수를 반환합니다.
     *
     * @return 등록된 활동 보고서의 총 개수
     * @created 2024-12-24
     */
    public int getTotalReportsCount() {
        return reports.size();
    }

    /**
     * 특정 동아리의 보고서 수를 반환합니다.
     *
     * @param clubName 동아리 이름
     * @return 해당 동아리의 보고서 개수
     * @created 2024-12-24
     */
    public int getReportsByClub(String clubName) {
        List<ActivityReport> clubReports = reports.get(clubName);
        if (clubReports == null) {
            return 0; // 해당 동아리에 보고서가 없으면 0 반환
        }
        return clubReports.size();
    }

    /**
     * 특정 기간 동안 작성된 보고서를 반환합니다.
     *
     * @param startDate 시작 날짜 (YYYY-MM-DD)
     * @param endDate 종료 날짜 (YYYY-MM-DD)
     * @return 해당 기간 동안 작성된 보고서 리스트
     * @created 2024-12-24
     */
    public List<ActivityReport> getReportsInDateRange(String startDate, String endDate) {
        List<ActivityReport> results = new ArrayList<>();
        for (List<ActivityReport> reportList : reports.values()) {
            for (ActivityReport report : reportList) {
                String date = report.getDate();
                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
                    results.add(report);
                }
            }
        }
        return results;
    }
}
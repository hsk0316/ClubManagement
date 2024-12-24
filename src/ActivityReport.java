import java.io.Serializable;

/**
 * 활동 보고서를 저장하는 클래스입니다.
 *
 * <p>
 * 이 클래스는 동아리 이름, 활동 내용, 작성자 이름, 위치, 결과, 작성 날짜와 같은 정보를 저장하고 관리합니다.
 * 활동 보고서의 세부 정보를 반환하는 기능도 제공합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.4
 * @since 2024-12-06
 *
 * @created 2024-12-06
 * @lastModified 2024-12-22
 *
 * @changelog
 * <ul>
 *   <li>2024-12-06: 최초 생성 (한승규)</li>
 *   <li>2024-12-07: getReportDetails 메서드 추가 (한승규)</li>
 *   <li>2024-12-08: Serializable 인터페이스 구현 및 직렬화 ID 추가 (한승규)</li>
 *   <li>2024-12-21: 날짜 및 키워드 기반 검색 기능 지원을 위한 필드와 메서드 추가 (한승규)</li>
 *   <li>2024-12-22: 작성자, 위치, 결과 필드 추가 및 메서드 업데이트 (한승규)</li>
 * </ul>
 */
public class ActivityReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clubName; // 동아리 이름
    private String activityContent; // 활동 내용
    private String date; // 보고서 작성 날짜
    private String content; // 활동 내용
    private String author;    // 작성자 이름
    private String location;  // 활동 위치
    private String result;    // 활동 결과

    /**
     * 활동 보고서 객체를 생성합니다.
     *
     * @param clubName 동아리 이름
     * @param activityContent 활동 내용
     * @param author 작성자 이름
     * @param location 활동 위치
     * @param result 활동 결과
     * @param date 보고서 작성 날짜
     */
    public ActivityReport(String clubName, String activityContent, String author, String location, String result, String date) {
        this.clubName = clubName;
        this.activityContent = activityContent;
        this.author = author;
        this.location = location;
        this.result = result;
        this.date = date;
        this.date = getCurrentDate();
    }

    /**
     * 동아리 이름을 반환합니다.
     *
     * @return 동아리 이름
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * 동아리 이름을 설정합니다.
     *
     * @param clubName 설정할 동아리 이름
     */
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    /**
     * 활동 내용을 반환합니다.
     *
     * @return 활동 내용
     */
    public String getActivityContent() {
        return activityContent;
    }

    /**
     * 활동 내용을 설정합니다.
     *
     * @param activityContent 설정할 활동 내용
     */
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    /**
     * 활동 보고서의 세부 정보를 반환합니다.
     *
     * <p>
     * 이 메서드는 동아리 이름, 작성자, 활동 내용, 위치, 결과, 작성 날짜와 같은 정보를 문자열 형태로 반환합니다.
     * </p>
     *
     * @return 활동 보고서 정보 문자열
     *
     * @created 2024-12-07
     * @lastModified 2024-12-22
     *
     * @changelog
     * <ul>
     *   <li>2024-12-07: 메서드 추가 (한승규)</li>
     *   <li>2024-12-08: Serializable 구현에 따른 변경 (한승규)</li>
     *   <li>2024-12-21: 작성 날짜 정보를 반환하도록 업데이트 (한승규)</li>
     *   <li>2024-12-22: 작성자, 위치, 결과 정보 포함하도록 업데이트 (한승규)</li>
     * </ul>
     */
    public String getReportDetails() {
        return "동아리 이름: " + clubName + "\n" +
                "작성자: " + author + "\n" +
                "활동 내용: " + activityContent + "\n" +
                "위치: " + location + "\n" +
                "결과: " + result + "\n" +
                "작성 날짜: " + date + "\n";
    }

    /**
     * 보고서 작성 날짜를 반환합니다.
     *
     * @return 작성 날짜 문자열 (예: 2024-12-21)
     */
    public String getDate() {
        return date;
    }

    /**
     * 작성자를 반환합니다.
     *
     * @return 작성자 이름
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 작성자를 설정합니다.
     *
     * @param author 설정할 작성자 이름
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 활동 위치를 반환합니다.
     *
     * @return 활동 위치
     */
    public String getLocation() {
        return location;
    }

    /**
     * 활동 위치를 설정합니다.
     *
     * @param location 설정할 활동 위치
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 활동 결과를 반환합니다.
     *
     * @return 활동 결과
     */
    public String getResult() {
        return result;
    }

    /**
     * 활동 결과를 설정합니다.
     *
     * @param result 설정할 활동 결과
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 보고서 작성 날짜를 설정합니다.
     *
     * @param date 설정할 작성 날짜
     */
    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    /**
     * 현재 날짜를 반환합니다.
     *
     * <p>
     * 이 메서드는 보고서 생성 시 자동으로 작성 날짜를 설정하기 위해 사용됩니다.
     * </p>
     *
     * @return 현재 날짜 문자열 (예: 2024-12-21)
     *
     * @created 2024-12-21
     */
    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }
}
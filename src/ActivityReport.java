import java.io.Serializable;

/**
 * 활동 보고서를 저장하는 클래스입니다.
 *
 * <p>
 * 이 클래스는 동아리 이름과 활동 내용을 저장하고 관리합니다.
 * 활동 보고서의 세부 정보를 반환하는 기능도 제공합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.2
 * @since 2024-12-06
 *
 * @created 2024-12-06
 * @lastModified 2024-12-08
 *
 * @changelog
 * <ul>
 *   <li>2024-12-06: 최초 생성 (한승규)</li>
 *   <li>2024-12-07: getReportDetails 메서드 추가 (한승규)</li>
 *   <li>2024-12-08: Serializable 인터페이스 구현 및 직렬화 ID 추가 (한승규)</li>
 * </ul>
 */
public class ActivityReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clubName; // 동아리 이름
    private String activityContent; // 활동 내용

    /**
     * 활동 보고서 객체를 생성합니다.
     *
     * @param clubName 동아리 이름
     * @param activityContent 활동 내용
     */
    public ActivityReport(String clubName, String activityContent) {
        this.clubName = clubName;
        this.activityContent = activityContent;
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
     * 활동 보고서 정보를 반환합니다.
     *
     * <p>
     * 이 메서드는 동아리 이름과 활동 내용을 문자열 형태로 반환합니다.
     * </p>
     *
     * @return 활동 보고서 정보 문자열
     *
     * @created 2024-12-07
     * @lastModified 2024-12-08
     *
     * @changelog
     * <ul>
     *   <li>2024-12-07: 메서드 추가 (한승규)</li>
     *   <li>2024-12-08: Serializable 구현에 따른 변경 (한승규)</li>
     * </ul>
     */
    public String getReportDetails() {
        return "동아리 이름: " + clubName +
                ", 활동 내용: " + activityContent;
    }
}
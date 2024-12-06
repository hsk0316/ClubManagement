/**
 * 활동 보고서를 저장하는 클래스입니다.
 *
 * <p>
 * 이 클래스는 동아리 이름과 활동 내용을 저장하고 관리합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.0
 * @since 2024-12-06
 *
 * @created 2024-12-06
 * @lastModified 2024-12-06
 */
public class ActivityReport {
    private String clubName;
    private String activityContent;

    public ActivityReport(String clubName, String activityContent) {
        this.clubName = clubName;
        this.activityContent = activityContent;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }
}
/**
 * 동아리 정보를 저장하는 클래스입니다.
 *
 * <p>
 * 이 클래스는 동아리 이름, 지도 교수, 회원 수, 설립 목적과 같은 동아리의 기본 정보를 관리합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.0
 * @since 2024-12-06
 *
 * @created 2024-12-06
 * @lastModified 2024-12-06
 */
public class Club {
    private String name;
    private String advisor;
    private int memberCount;
    private String purpose;

    public Club(String name, String advisor, int memberCount, String purpose) {
        this.name = name;
        this.advisor = advisor;
        this.memberCount = memberCount;
        this.purpose = purpose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
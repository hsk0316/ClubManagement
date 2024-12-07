/**
 * 동아리 정보를 저장하는 클래스입니다.
 *
 * <p>
 * 이 클래스는 동아리 이름, 지도 교수, 회원 수, 설립 목적과 같은 동아리의 기본 정보를 관리합니다.
 * </p>
 *
 * @author 한승규
 * @version 1.1
 * @since 2024-12-06
 *
 * @created 2024-12-06
 * @lastModified 2024-12-07
 *
 * @changelog
 * <ul>
 *   <li>2024-12-06: 최초 생성 (한승규)</li>
 *   <li>2024-12-07: getDetails 메서드 추가 및 전체 코드 개선 (한승규)</li>
 * </ul>
 */
public class Club {
    private String name;
    private String advisor;
    private int memberCount;
    private String purpose;

    /**
     * 동아리 객체를 생성합니다.
     *
     * @param name 동아리 이름
     * @param advisor 지도 교수
     * @param memberCount 회원 수
     * @param purpose 설립 목적
     */
    public Club(String name, String advisor, int memberCount, String purpose) {
        this.name = name;
        this.advisor = advisor;
        this.memberCount = memberCount;
        this.purpose = purpose;
    }

    /**
     * 동아리 이름을 반환합니다.
     *
     * @return 동아리 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 동아리 이름을 설정합니다.
     *
     * @param name 설정할 동아리 이름
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 지도 교수를 반환합니다.
     *
     * @return 지도 교수 이름
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * 지도 교수를 설정합니다.
     *
     * @param advisor 설정할 지도 교수 이름
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    /**
     * 회원 수를 반환합니다.
     *
     * @return 회원 수
     */
    public int getMemberCount() {
        return memberCount;
    }

    /**
     * 회원 수를 설정합니다.
     *
     * @param memberCount 설정할 회원 수
     */
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    /**
     * 설립 목적을 반환합니다.
     *
     * @return 설립 목적
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * 설립 목적을 설정합니다.
     *
     * @param purpose 설정할 설립 목적
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * 동아리 정보를 반환합니다.
     *
     * <p>
     * 이 메서드는 동아리의 이름, 지도 교수, 회원 수, 설립 목적 정보를 문자열 형태로 반환합니다.
     * </p>
     *
     * @return 동아리 정보 문자열
     *
     * @created 2024-12-07
     * @lastModified 2024-12-07
     *
     * @changelog
     * <ul>
     *   <li>2024-12-07: 메서드 추가 (한승규)</li>
     * </ul>
     */
    public String getDetails() {
        return "동아리 이름: " + name +
                ", 지도 교수: " + advisor +
                ", 회원 수: " + memberCount +
                ", 설립 목적: " + purpose;
    }
}
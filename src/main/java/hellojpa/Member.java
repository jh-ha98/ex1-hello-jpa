package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	// @Column(name = "TEAM_ID")
	// private Long teamId;
	
	// FetchType.LAZY: 지연 로딩, member 클래스만 DB에서 조회
	// @ManyToOne(fetch = FetchType.LAZY)

	// FetchType.EAGER: 즉시 로딩, team과 member 조인해서 한번에 조회
	// @ManyToOne(fetch = FetchType.EAGER)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}

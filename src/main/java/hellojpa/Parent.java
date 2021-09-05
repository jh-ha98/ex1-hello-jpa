package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parent {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  // 단일 entity 관리 할 때만 CascadeType.ALL 사용
  // lifecycle이 같을 때, 단일 소유자 일때

  // orphanRemoval = true일때 하위 자식들도 삭제
  // @OneToMany(mappedBy = "parent", /* cascade = CascadeType.ALL, */ orphanRemoval = true)

  // orphanRemoval = false일때 부모만 삭제
  // @OneToMany(mappedBy = "parent", /* cascade = CascadeType.ALL, */ orphanRemoval = false)

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  // @OneToMany(mappedBy = "parent")
  private List<Child> childList = new ArrayList<Child>();

  public void addChild(Child child) {
    childList.add(child);
    child.setParent(this);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Child> getChildList() {
    return childList;
  }
}

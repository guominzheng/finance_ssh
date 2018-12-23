package server.com.zbm.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * 法人表
 */
@Data
@Entity
@Table(name = "tb_legal")
public class Legal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "l_id", unique = true, nullable = false)
    private Long id;
    @Column(name = "l_name")
    private String lname;
    @Column(name = "l_phone")
    private String phone;
    @Column(name = "l_wxName")
    private String wxName;
    @Column(name = "l_openid")
    private String openid;
    @Column(name = "l_img")
    private String img;
    @Column(name = "l_sex")
    private char sex;
    @Column(name = "l_address")
    private String address;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "updateTime")
    private String updateTime;
    @OneToMany(targetEntity = Company.class)
    @JoinColumn(name = "l_id")
    private List<Company> companies;
}

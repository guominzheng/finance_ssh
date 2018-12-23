package server.com.zbm.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 公司信息表
 */
@Data
@Entity
@Table(name = "tb_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", unique = true, nullable = false)
    private Long id;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_address")
    private String address;
    @Column(name = "c_phone")
    private String phone;
    @Column(name = "c_charterZ")
    private String charTerz;
    @Column(name = "c_charterF")
    private String charTerf;
    @Column(name = "c_scale")
    private Integer scale;
    @Column(name = "c_countMoney")
    private Double money;
    @Column(name = "c_createTime")
    private String createTime;
    @Column(name = "c_updateTime")
    private String updateTime;
    @ManyToOne(targetEntity = Legal.class)
    @JoinColumn(name = "l_id")
    private Legal legal;
}

package server.com.zbm.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "u_name")
    private String username;

    @Column(name = "u_sex")
    private String sex;

    @Column(name = "u_address")
    private String address;
}

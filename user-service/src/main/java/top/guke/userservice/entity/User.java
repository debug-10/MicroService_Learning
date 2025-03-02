package top.guke.userservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("t_user")
public class User {
    private Integer id;
    private String mobile;
    private String password;
    private String userName;
    private String avatarUrl;
    private Date createTime;
    private Date updateTime;
}
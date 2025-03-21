package top.guke.contentservice.model.vo;

import lombok.Data;

@Data
public class ShareVo {
    private Integer id;
    private String title;
    private String createTime;
    private String updateTime;
    private Boolean isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
    private Integer buyCount;
    private Boolean showFlag;
    private String auditStatus;
    private String reason;
    private UserVo authorInfo;
}
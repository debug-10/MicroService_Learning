package top.guke.contentservice.model.vo;

import lombok.Data;

@Data
public class ShareVO {
    private Integer id;
    private String title;
    private String authorName;
    private String authorAvatar;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
}
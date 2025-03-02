package top.guke.contentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guke.contentservice.mapper.ShareMapper;
import top.guke.contentservice.model.DTO.UserDTO;
import top.guke.contentservice.model.entity.Share;
import top.guke.contentservice.model.vo.ShareVO;
import top.guke.contentservice.openfeign.UserFeignClient;

@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public ShareVO getShareById(@PathVariable Integer id) {
        Share share = shareMapper.selectById(id);

        UserDTO author = userFeignClient.getUserById(share.getUserId());

        ShareVO shareVO = new ShareVO();
        shareVO.setId(share.getId());
        shareVO.setTitle(share.getTitle());
        shareVO.setAuthorName(author.getUserName());
        shareVO.setAuthorAvatar(author.getAvatarUrl());
        shareVO.setCover(share.getCover());
        shareVO.setSummary(share.getSummary());
        shareVO.setPrice(share.getPrice());
        shareVO.setDownloadUrl(share.getDownloadUrl());

        return shareVO;
    }
}
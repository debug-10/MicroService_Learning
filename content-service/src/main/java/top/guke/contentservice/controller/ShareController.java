package top.guke.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.model.v2.Result;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guke.contentservice.mapper.ShareMapper;
import top.guke.contentservice.model.DTO.UserDTO;
import top.guke.contentservice.model.entity.Share;
import top.guke.contentservice.model.vo.ShareVo;
import top.guke.contentservice.model.vo.UserVo;
import top.guke.contentservice.openfeign.UserFeignClient;
import top.guke.contentservice.service.ShareService;

@RestController
@AllArgsConstructor
public class ShareController {

    @Resource
    private ShareService shareService;

    @Resource
    private UserFeignClient userServiceClient;

    @GetMapping("/share/{id}")
    @SentinelResource(value = "/share/{id}")
    public ShareVo getShareById(@PathVariable Integer id) {
        Share share = shareService.getById(id);

        Result<UserVo> result = userServiceClient.getUserById(share.getUserId());
        UserVo userVo = result.getData();

        ShareVo shareVO = new ShareVo();
        shareVO.setId(share.getId());
        shareVO.setTitle(share.getTitle());
        shareVO.setCreateTime(share.getCreateTime().toString());
        shareVO.setUpdateTime(share.getUpdateTime().toString());
        shareVO.setIsOriginal(share.getIsOriginal());
        shareVO.setAuthor(share.getAuthor());
        shareVO.setCover(share.getCover());
        shareVO.setSummary(share.getSummary());
        shareVO.setPrice(share.getPrice());
        shareVO.setDownloadUrl(share.getDownloadUrl());
        shareVO.setBuyCount(share.getBuyCount());
        shareVO.setShowFlag(share.getShowFlag());
        shareVO.setAuditStatus(share.getAuditStatus());
        shareVO.setReason(share.getReason());
        shareVO.setAuthorInfo(userVo);

        return shareVO;
    }

}
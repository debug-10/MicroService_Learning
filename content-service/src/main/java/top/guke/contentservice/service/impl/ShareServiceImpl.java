package top.guke.contentservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.guke.contentservice.mapper.ShareMapper;
import top.guke.contentservice.model.entity.Share;
import top.guke.contentservice.service.ShareService;

@Service
public class ShareServiceImpl extends ServiceImpl <ShareMapper , Share>implements ShareService {
}

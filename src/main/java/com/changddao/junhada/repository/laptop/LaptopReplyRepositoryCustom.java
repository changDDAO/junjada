package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.laptop.LaptopReply;

import java.util.List;

public interface LaptopReplyRepositoryCustom {
    public List<LaptopReply> findLaptopRepliesByMember(Long memberId);

}
